CREATE EXTENSION IF NOT EXISTS pg_trgm;


CREATE TABLE IF NOT EXISTS users
(
    id                          VARCHAR                                NOT NULL,
    login                       VARCHAR                                NOT NULL,
    first_name                  VARCHAR,
    last_name                   VARCHAR,
    email                       VARCHAR,
    activated                   BOOLEAN                                NOT NULL DEFAULT FALSE,
    lang_key                    VARCHAR,
    default_billing_address_id  varchar
        constraint "REL_8abe81b9aac151ae60bf507ad1" unique,
    default_shipping_address_id varchar
        constraint "REL_8abe81b9aac151ae60bf507ag3" unique,
    phone                       varchar,
    note                        varchar,
    image_url                   varchar,
    created_at                  timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at                  timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at                  timestamp WITH TIME ZONE,
    last_modified_date          TIMESTAMP,
    meta_data                   jsonb,

    CONSTRAINT users_pk PRIMARY KEY (id),
    CONSTRAINT ux_user_login UNIQUE (login),
    CONSTRAINT ux_user_email UNIQUE (email)
);
CREATE INDEX IF NOT EXISTS "IDX_8abe81b9aac151ae60bf507ad1"
    on users (default_billing_address_id);
CREATE INDEX IF NOT EXISTS "IDX_8abe81b9aac151ae60bf507ag3"
    on users (default_shipping_address_id);


CREATE TABLE IF NOT EXISTS authority
(
    name VARCHAR NOT NULL,
    CONSTRAINT authority_pk PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS user_authorities
(
    user_id        VARCHAR NOT NULL,
    authority_name VARCHAR NOT NULL,

    CONSTRAINT user_authorities_pk PRIMARY KEY (user_id, authority_name),
    CONSTRAINT fk_user_authorities_user FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_user_authorities_authority FOREIGN KEY (authority_name)
        REFERENCES authority (name)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS api_key
(
    id          VARCHAR                                NOT NULL,
    user_id     VARCHAR                                NOT NULL,
    key         VARCHAR                                NOT NULL,
    description VARCHAR,
    created_at  timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at  timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at  timestamp WITH TIME ZONE,

    CONSTRAINT api_key_pk PRIMARY KEY (id),
    CONSTRAINT fk_api_key_user FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS fulfillment_provider
(
    id           varchar              NOT NULL
        CONSTRAINT "PK_beb35a6de60a6c4f91d5ae57e44" PRIMARY KEY,
    is_installed boolean DEFAULT TRUE NOT NULL
);


CREATE TABLE IF NOT EXISTS currency
(
    code          varchar NOT NULL
        CONSTRAINT "PK_723472e41cae44beb0763f4039c" PRIMARY KEY,
    symbol        varchar NOT NULL,
    symbol_native varchar NOT NULL,
    name          varchar NOT NULL
);


CREATE TABLE IF NOT EXISTS payment_provider
(
    id           varchar              NOT NULL
        CONSTRAINT "PK_ea94f42b6c88e9191c3649d7522" PRIMARY KEY,
    is_installed boolean DEFAULT TRUE NOT NULL
);


CREATE TABLE IF NOT EXISTS image
(
    id         VARCHAR                                NOT NULL
        CONSTRAINT "PK_d6db1ab4ee9ad9dbe86c64e4cc3" PRIMARY KEY,
    url        varchar                                NOT NULL,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at timestamp WITH TIME ZONE,
    metadata   JSONB
);

create type SHIPPING_PROFILE_TYPE as enum ('DEFAULT', 'GIFT_CARD', 'CUSTOM');

CREATE TABLE IF NOT EXISTS shipping_profile
(
    id         VARCHAR                                NOT NULL
        CONSTRAINT "PK_c8120e4543a5a3a121f2968a1ec" PRIMARY KEY,
    name       varchar                                NOT NULL,
    TYPE       SHIPPING_PROFILE_TYPE                  NOT NULL,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at timestamp WITH TIME ZONE,
    metadata   JSONB
);

create type DISCOUNT_RULE_TYPE as enum ('FIXED', 'PERCENTAGE', 'FREE_SHIPPING');
create type DISCOUNT_RULE_ALLOCATION_TYPE as enum ('TOTAL', 'ITEM');

CREATE TABLE IF NOT EXISTS discount_rule
(
    id          VARCHAR                                NOT NULL
        CONSTRAINT "PK_ac2c280de3701b2d66f6817f760" PRIMARY KEY,
    description varchar,
    TYPE        DISCOUNT_RULE_TYPE                     NOT NULL,
    value       integer                                NOT NULL,
    allocation  DISCOUNT_RULE_ALLOCATION_TYPE,
    created_at  timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at  timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at  timestamp WITH TIME ZONE,
    metadata    JSONB
);


CREATE TABLE IF NOT EXISTS discount
(
    id                 VARCHAR                                            NOT NULL
        CONSTRAINT "PK_d05d8712e429673e459e7f1cddb" PRIMARY KEY,
    code               varchar                                            NOT NULL,
    is_dynamic         boolean                                            NOT NULL,
    rule_id            VARCHAR
        CONSTRAINT "FK_ac2c280de3701b2d66f6817f760" REFERENCES discount_rule,
    is_disabled        boolean                                            NOT NULL,
    parent_discount_id VARCHAR
        CONSTRAINT "FK_2250c5d9e975987ab212f61a663" REFERENCES discount,
    starts_at          timestamp WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ends_at            timestamp WITH TIME ZONE,
    created_at         timestamp WITH TIME ZONE DEFAULT now()             NOT NULL,
    updated_at         timestamp WITH TIME ZONE DEFAULT now()             NOT NULL,
    deleted_at         timestamp WITH TIME ZONE,
    metadata           JSONB,
    usage_limit        integer,
    usage_count        integer                  DEFAULT 0                 NOT NULL,
    valid_duration     varchar
);

CREATE INDEX IF NOT EXISTS "IDX_ac2c280de3701b2d66f6817f76" ON discount (rule_id);
CREATE UNIQUE INDEX IF NOT EXISTS "IDX_f65bf52e2239ace276ece2b2f4" ON discount (code)
    WHERE (deleted_at IS NULL);


CREATE TABLE IF NOT EXISTS payment
(
    id              VARCHAR                                NOT NULL
        CONSTRAINT "PK_fcaec7df5adf9cac408c686b2ab" PRIMARY KEY,
    swap_id         VARCHAR
        CONSTRAINT "REL_c17aff091441b7c25ec3d68d36" UNIQUE,
    cart_id         VARCHAR,
    order_id        VARCHAR,
    amount          integer                                NOT NULL,
    currency_code   varchar                                NOT NULL
        CONSTRAINT "FK_f41553459a4b1491c9893ebc921" REFERENCES currency,
    amount_refunded integer                  DEFAULT 0     NOT NULL,
    provider_id     varchar                                NOT NULL,
    DATA            JSONB                                  NOT NULL,
    captured_at     timestamp WITH TIME ZONE,
    canceled_at     timestamp WITH TIME ZONE,
    created_at      timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at      timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata        JSONB,
    idempotency_key varchar
);

CREATE INDEX IF NOT EXISTS "IDX_c17aff091441b7c25ec3d68d36" ON payment (swap_id);
CREATE INDEX IF NOT EXISTS "IDX_4665f17abc1e81dd58330e5854" ON payment (cart_id);
CREATE INDEX IF NOT EXISTS "IDX_f5221735ace059250daac9d980" ON payment (order_id);
CREATE INDEX IF NOT EXISTS "IDX_ea94f42b6c88e9191c3649d752" ON payment (provider_id);
CREATE UNIQUE INDEX IF NOT EXISTS "UniquePaymentActive" ON payment (cart_id)
    WHERE (canceled_at IS NULL);
CREATE INDEX IF NOT EXISTS "IDX_aac4855eadda71aa1e4b6d7684" ON payment (cart_id)
    WHERE (canceled_at IS NOT NULL);
CREATE INDEX IF NOT EXISTS "IDX_payment_currency_code" ON payment (currency_code);


CREATE TABLE IF NOT EXISTS idempotency_key
(
    id              varchar                                                       NOT NULL
        CONSTRAINT "PK_213f125e14469be304f9ff1d452" PRIMARY KEY,
    idempotency_key varchar                                                       NOT NULL,
    created_at      timestamp WITH TIME ZONE DEFAULT now()                        NOT NULL,
    locked_at       timestamp WITH TIME ZONE,
    request_method  varchar,
    request_params  JSONB,
    request_path    varchar,
    response_code   integer,
    response_body   JSONB,
    recovery_point  varchar                  DEFAULT 'started'::CHARACTER NOT NULL
);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_a421bf4588d0004a9b0c0fe84f" ON idempotency_key (idempotency_key);


CREATE TABLE IF NOT EXISTS oauth
(
    id               varchar NOT NULL
        CONSTRAINT "PK_a957b894e50eb16b969c0640a8d" PRIMARY KEY,
    display_name     varchar NOT NULL,
    application_name varchar NOT NULL,
    install_url      varchar,
    uninstall_url    varchar,
    DATA             JSONB
);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_c49c061b1a686843c5d673506f" ON oauth (application_name);


CREATE TABLE IF NOT EXISTS staged_job
(
    id         varchar                   NOT NULL
        CONSTRAINT "PK_9a28fb48c46c5509faf43ac8c8d" PRIMARY KEY,
    event_name varchar                   NOT NULL,
    DATA       JSONB                     NOT NULL,
    OPTIONS    JSONB DEFAULT '{}'::JSONB NOT NULL
);

CREATE TABLE IF NOT EXISTS claim_tag
(
    id         varchar                                NOT NULL
        CONSTRAINT "PK_7761180541142a5926501018d34" PRIMARY KEY,
    value      varchar                                NOT NULL,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at timestamp WITH TIME ZONE,
    metadata   JSONB
);


CREATE INDEX IF NOT EXISTS "IDX_ec10c54769877840c132260e4a" ON claim_tag (value);


CREATE TABLE IF NOT EXISTS notification_provider
(
    id           varchar              NOT NULL
        CONSTRAINT "PK_0425c2423e2ce9fdfd5c23761d9" PRIMARY KEY,
    is_installed boolean DEFAULT TRUE NOT NULL
);


CREATE TABLE IF NOT EXISTS notification
(
    id            varchar                                NOT NULL
        CONSTRAINT "PK_705b6c7cdf9b2c2ff7ac7872cb7" PRIMARY KEY,
    event_name    varchar,
    resource_type varchar                                NOT NULL,
    resource_id   varchar                                NOT NULL,
    user_id       varchar
        CONSTRAINT "FK_b5df0f53a74b9d0c0a2b652c88d" REFERENCES users,
    "to"          varchar                                NOT NULL,
    DATA          JSONB                                  NOT NULL,
    parent_id     varchar
        CONSTRAINT "FK_371db513192c083f48ba63c33be" REFERENCES notification,
    provider_id   varchar
        CONSTRAINT "FK_0425c2423e2ce9fdfd5c23761d9" REFERENCES notification_provider,
    created_at    timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at    timestamp WITH TIME ZONE DEFAULT now() NOT NULL
);


CREATE INDEX IF NOT EXISTS "IDX_df1494d263740fcfb1d09a98fc" ON notification (resource_type);


CREATE INDEX IF NOT EXISTS "IDX_ea6a358d9ce41c16499aae55f9" ON notification (resource_id);


CREATE INDEX IF NOT EXISTS "IDX_b5df0f53a74b9d0c0a2b652c88" ON notification (user_id);


CREATE TABLE IF NOT EXISTS product_collection
(
    id         varchar                                NOT NULL
        CONSTRAINT "PK_49d419fc77d3aed46c835c558ac" PRIMARY KEY,
    title      varchar                                NOT NULL,
    handle     varchar,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at timestamp WITH TIME ZONE,
    metadata   JSONB
);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_6f234f058bbbea810dce1d04d0" ON product_collection (handle)
    WHERE (deleted_at IS NULL);


CREATE INDEX IF NOT EXISTS idx_gin_product_collection ON product_collection USING gin (title gin_trgm_ops)
    WHERE (deleted_at IS NULL);


CREATE TABLE IF NOT EXISTS product_tag
(
    id         varchar                                NOT NULL
        CONSTRAINT "PK_1439455c6528caa94fcc8564fda" PRIMARY KEY,
    value      varchar                                NOT NULL,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at timestamp WITH TIME ZONE,
    metadata   JSONB
);


CREATE TABLE IF NOT EXISTS product_type
(
    id         varchar                                NOT NULL
        CONSTRAINT "PK_e0843930fbb8854fe36ca39dae1" PRIMARY KEY,
    value      varchar                                NOT NULL,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at timestamp WITH TIME ZONE,
    metadata   JSONB
);

create type PRODUCT_STATUS_TYPE as enum ('DRAFT', 'PROPOSED', 'PUBLISHED', 'REJECTED');
CREATE TABLE IF NOT EXISTS product
(
    id             varchar                                                       NOT NULL
        CONSTRAINT "PK_bebc9158e480b949565b4dc7a82" PRIMARY KEY,
    title          varchar                                                       NOT NULL,
    subtitle       varchar,
    description    varchar,
    handle         varchar,
    is_giftcard    boolean                  DEFAULT FALSE                        NOT NULL,
    thumbnail      varchar,
    weight         integer,
    LENGTH         integer,
    height         integer,
    width          integer,
    hs_code        varchar,
    origin_country varchar,
    mid_code       varchar,
    material       varchar,
    created_at     timestamp WITH TIME ZONE DEFAULT now()                        NOT NULL,
    updated_at     timestamp WITH TIME ZONE DEFAULT now()                        NOT NULL,
    deleted_at     timestamp WITH TIME ZONE,
    metadata       JSONB,
    collection_id  varchar
        CONSTRAINT "FK_49d419fc77d3aed46c835c558ac" REFERENCES product_collection,
    type_id        varchar
        CONSTRAINT "FK_e0843930fbb8854fe36ca39dae1" REFERENCES product_type,
    discountable   boolean                  DEFAULT TRUE                         NOT NULL,
    status         PRODUCT_STATUS_TYPE      DEFAULT 'DRAFT'::PRODUCT_STATUS_TYPE NOT NULL,
    external_id    varchar
);


CREATE TABLE IF NOT EXISTS product_variant
(
    id                 varchar                                NOT NULL
        CONSTRAINT "PK_1ab69c9935c61f7c70791ae0a9f" PRIMARY KEY,
    title              varchar                                NOT NULL,
    product_id         varchar                                NOT NULL
        CONSTRAINT "FK_ca67dd080aac5ecf99609960cd2" REFERENCES product,
    sku                varchar,
    barcode            varchar,
    ean                varchar,
    upc                varchar,
    inventory_quantity integer                                NOT NULL,
    allow_backorder    boolean                  DEFAULT FALSE NOT NULL,
    manage_inventory   boolean                  DEFAULT TRUE  NOT NULL,
    hs_code            varchar,
    origin_country     varchar,
    mid_code           varchar,
    material           varchar,
    weight             integer,
    LENGTH             integer,
    height             integer,
    width              integer,
    created_at         timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at         timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at         timestamp WITH TIME ZONE,
    metadata           JSONB,
    variant_rank       integer                  DEFAULT 0
);


CREATE INDEX IF NOT EXISTS "IDX_ca67dd080aac5ecf99609960cd" ON product_variant (product_id);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_2ca8cfbdafb998ecfd6d340389" ON product_variant (sku)
    WHERE (deleted_at IS NULL);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_045d4a149c09f4704e0bc08dd4" ON product_variant (barcode)
    WHERE (deleted_at IS NULL);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_b5b6225539ee8501082fbc0714" ON product_variant (ean)
    WHERE (deleted_at IS NULL);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_aa16f61348be02dd07ce3fc54e" ON product_variant (upc)
    WHERE (deleted_at IS NULL);


CREATE INDEX IF NOT EXISTS idx_gin_product_variant_title ON product_variant USING gin (title gin_trgm_ops)
    WHERE (deleted_at IS NULL);


CREATE INDEX IF NOT EXISTS idx_gin_product_variant_sku ON product_variant USING gin (sku gin_trgm_ops)
    WHERE (deleted_at IS NULL);


CREATE TABLE IF NOT EXISTS product_option
(
    id         varchar                                NOT NULL
        CONSTRAINT "PK_4cf3c467e9bc764bdd32c4cd938" PRIMARY KEY,
    title      varchar                                NOT NULL,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at timestamp WITH TIME ZONE,
    metadata   JSONB,
    product_id varchar
        CONSTRAINT "FK_e634fca34f6b594b87fdbee95f6" REFERENCES product
);


CREATE TABLE IF NOT EXISTS product_option_value
(
    id         varchar                                NOT NULL
        CONSTRAINT "PK_2ab71ed3b21be5800905c621535" PRIMARY KEY,
    value      varchar                                NOT NULL,
    option_id  varchar                                NOT NULL
        CONSTRAINT "FK_cdf4388f294b30a25c627d69fe9" REFERENCES product_option,
    variant_id varchar                                NOT NULL
        CONSTRAINT "FK_7234ed737ff4eb1b6ae6e6d7b01" REFERENCES product_variant ON DELETE CASCADE,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at timestamp WITH TIME ZONE,
    metadata   JSONB
);


CREATE INDEX IF NOT EXISTS idx_product_option_value_variant_id ON product_option_value (variant_id);


CREATE INDEX IF NOT EXISTS idx_product_option_value_option_id ON product_option_value (option_id);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_cf9cc6c3f2e6414b992223fff1" ON product (handle)
    WHERE (deleted_at IS NULL);


CREATE INDEX IF NOT EXISTS idx_gin_product_title ON product USING gin (title gin_trgm_ops)
    WHERE (deleted_at IS NULL);


CREATE INDEX IF NOT EXISTS idx_gin_product_description ON product USING gin (description gin_trgm_ops)
    WHERE (deleted_at IS NULL);


CREATE TABLE IF NOT EXISTS product_images
(
    product_id varchar NOT NULL
        CONSTRAINT "FK_4f166bb8c2bfcef2498d97b4068" REFERENCES product ON DELETE CASCADE,
    image_id   varchar NOT NULL
        CONSTRAINT "FK_2212515ba306c79f42c46a99db7" REFERENCES image ON DELETE CASCADE,
    CONSTRAINT "PK_10de97980da2e939c4c0e8423f2" PRIMARY KEY (product_id,
                                                             image_id)
);


CREATE INDEX IF NOT EXISTS "IDX_4f166bb8c2bfcef2498d97b406" ON product_images (product_id);


CREATE INDEX IF NOT EXISTS "IDX_2212515ba306c79f42c46a99db" ON product_images (image_id);


CREATE TABLE IF NOT EXISTS discount_rule_products
(
    discount_rule_id varchar NOT NULL
        CONSTRAINT "FK_4e0739e5f0244c08d41174ca08a" REFERENCES discount_rule ON DELETE CASCADE,
    product_id       varchar NOT NULL
        CONSTRAINT "FK_be66106a673b88a81c603abe7eb" REFERENCES product ON DELETE CASCADE,
    CONSTRAINT "PK_351c8c92f5d27283c445cd022ee" PRIMARY KEY (discount_rule_id,
                                                             product_id)
);


CREATE INDEX IF NOT EXISTS "IDX_4e0739e5f0244c08d41174ca08" ON discount_rule_products (discount_rule_id);


CREATE INDEX IF NOT EXISTS "IDX_be66106a673b88a81c603abe7e" ON discount_rule_products (product_id);


CREATE TABLE IF NOT EXISTS product_tags
(
    product_id     varchar NOT NULL
        CONSTRAINT "FK_5b0c6fc53c574299ecc7f9ee22e" REFERENCES product ON DELETE CASCADE,
    product_tag_id varchar NOT NULL
        CONSTRAINT "FK_21683a063fe82dafdf681ecc9c4" REFERENCES product_tag ON DELETE CASCADE,
    CONSTRAINT "PK_1cf5c9537e7198df494b71b993f" PRIMARY KEY (product_id,
                                                             product_tag_id)
);


CREATE INDEX IF NOT EXISTS "IDX_5b0c6fc53c574299ecc7f9ee22" ON product_tags (product_id);


CREATE INDEX IF NOT EXISTS "IDX_21683a063fe82dafdf681ecc9c" ON product_tags (product_tag_id);


CREATE TABLE IF NOT EXISTS return_reason
(
    id                      varchar                                NOT NULL
        CONSTRAINT "PK_95fd1172973165790903e65660a" PRIMARY KEY,
    value                   varchar                                NOT NULL,
    label                   varchar                                NOT NULL,
    description             varchar,
    created_at              timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at              timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at              timestamp WITH TIME ZONE,
    metadata                JSONB,
    parent_return_reason_id varchar
        CONSTRAINT "FK_2250c5d9e975987ab212f61a657" REFERENCES return_reason
);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_return_reason_value" ON return_reason (value)
    WHERE (deleted_at IS NULL);


CREATE TABLE IF NOT EXISTS note
(
    id            varchar                                NOT NULL
        CONSTRAINT "PK_96d0c172a4fba276b1bbed43058" PRIMARY KEY,
    value         varchar                                NOT NULL,
    resource_type varchar                                NOT NULL,
    resource_id   varchar                                NOT NULL,
    author_id     varchar,
    created_at    timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at    timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at    timestamp WITH TIME ZONE,
    metadata      JSONB
);


CREATE INDEX IF NOT EXISTS "IDX_f74980b411cf94af523a72af7d" ON note (resource_type);


CREATE INDEX IF NOT EXISTS "IDX_3287f98befad26c3a7dab088cf" ON note (resource_id);

create type INVITE_ROLE_TYPE as enum ('ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_DEVELOPER', 'ROLE_CUSTOMER');
CREATE TABLE IF NOT EXISTS invite
(
    id         varchar                                NOT NULL
        CONSTRAINT "PK_fc9fa190e5a3c5d80604a4f63e1" PRIMARY KEY,
    user_email varchar                                NOT NULL,
    ROLE       INVITE_ROLE_TYPE         DEFAULT 'ROLE_CUSTOMER'::INVITE_ROLE_TYPE,
    accepted   boolean                  DEFAULT FALSE NOT NULL,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at timestamp WITH TIME ZONE,
    metadata   JSONB,
    token      varchar                                NOT NULL,
    expires_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL
);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_6b0ce4b4bcfd24491510bf19d1" ON invite (user_email);


CREATE TABLE IF NOT EXISTS tax_provider
(
    id           varchar              NOT NULL
        CONSTRAINT "PK_b198bf82ba6a317c11763d99b99" PRIMARY KEY,
    is_installed boolean DEFAULT TRUE NOT NULL
);


CREATE TABLE IF NOT EXISTS region
(
    id                 varchar                                NOT NULL
        CONSTRAINT "PK_5f48ffc3af96bc486f5f3f3a6da" PRIMARY KEY,
    name               varchar                                NOT NULL,
    currency_code      varchar                                NOT NULL
        CONSTRAINT "FK_3bdd5896ec93be2f1c62a3309a5" REFERENCES currency,
    tax_rate           real                                   NOT NULL,
    tax_code           varchar,
    created_at         timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at         timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at         timestamp WITH TIME ZONE,
    metadata           JSONB,
    gift_cards_taxable boolean                  DEFAULT TRUE  NOT NULL,
    automatic_taxes    boolean                  DEFAULT TRUE  NOT NULL,
    tax_provider_id    varchar
        CONSTRAINT "FK_91f88052197680f9790272aaf5b" REFERENCES tax_provider
);


CREATE TABLE IF NOT EXISTS country
(
    id           serial
        CONSTRAINT "PK_bf6e37c231c4f4ea56dcd887269" PRIMARY KEY,
    iso_2        varchar NOT NULL,
    iso_3        varchar NOT NULL,
    num_code     integer NOT NULL,
    name         varchar NOT NULL,
    display_name varchar NOT NULL,
    region_id    varchar
        CONSTRAINT "FK_b1aac8314662fa6b25569a575bb" REFERENCES region
);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_e78901b1131eaf8203d9b1cb5f" ON country (iso_2);
CREATE INDEX IF NOT EXISTS "IDX_b1aac8314662fa6b25569a575b" ON country (region_id);
CREATE INDEX IF NOT EXISTS "IDX_region_currency_code" ON region (currency_code);


create type SHIPPING_OPTION_PRICE_TYPE as enum ('FLAT_RATE', 'CALCULATED');
CREATE TABLE IF NOT EXISTS shipping_option
(
    id          varchar                                NOT NULL
        CONSTRAINT "PK_2e56fddaa65f3a26d402e5d786e" PRIMARY KEY,
    name        varchar                                NOT NULL,
    region_id   varchar                                NOT NULL
        CONSTRAINT "FK_5c58105f1752fca0f4ce69f4663" REFERENCES region,
    profile_id  varchar                                NOT NULL
        CONSTRAINT "FK_c951439af4c98bf2bd7fb8726cd" REFERENCES shipping_profile,
    provider_id varchar                                NOT NULL
        CONSTRAINT "FK_a0e206bfaed3cb63c1860917347" REFERENCES fulfillment_provider,
    price_type  SHIPPING_OPTION_PRICE_TYPE             NOT NULL,
    amount      integer
        CONSTRAINT "CHK_7a367f5901ae0a5b0df75aee38" CHECK (amount >= 0),
    is_return   boolean                  DEFAULT FALSE NOT NULL,
    DATA        JSONB                                  NOT NULL,
    created_at  timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at  timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at  timestamp WITH TIME ZONE,
    metadata    JSONB,
    admin_only  boolean                  DEFAULT FALSE NOT NULL
);


create type SHIPPING_OPTION_REQUIREMENT_TYPE as enum ('MIN_SUBTOTAL', 'MAX_SUBTOTAL');
CREATE TABLE IF NOT EXISTS shipping_option_requirement
(
    id                 varchar                          NOT NULL
        CONSTRAINT "PK_a0ff15442606d9f783602cb23a7" PRIMARY KEY,
    shipping_option_id varchar                          NOT NULL
        CONSTRAINT "FK_012a62ba743e427b5ebe9dee18e" REFERENCES shipping_option,
    TYPE               SHIPPING_OPTION_REQUIREMENT_TYPE NOT NULL,
    amount             integer                          NOT NULL,
    deleted_at         timestamp WITH TIME ZONE
);


CREATE INDEX IF NOT EXISTS "IDX_012a62ba743e427b5ebe9dee18" ON shipping_option_requirement (shipping_option_id);
CREATE INDEX IF NOT EXISTS "IDX_5c58105f1752fca0f4ce69f466" ON shipping_option (region_id);
CREATE INDEX IF NOT EXISTS "IDX_c951439af4c98bf2bd7fb8726c" ON shipping_option (profile_id);
CREATE INDEX IF NOT EXISTS "IDX_a0e206bfaed3cb63c186091734" ON shipping_option (provider_id);

CREATE TABLE IF NOT EXISTS address
(
    id           varchar                                NOT NULL
        CONSTRAINT "PK_d92de1f82754668b5f5f5dd4fd5" PRIMARY KEY,
    user_id      varchar
        CONSTRAINT "FK_9c9614b2f9d01665800ea8dbff7" REFERENCES users,
    company      varchar,
    first_name   varchar,
    last_name    varchar,
    address_1    varchar,
    address_2    varchar,
    city         varchar,
    country_code varchar
        CONSTRAINT "FK_6df8c6bf969a51d24c1980c4ff4" REFERENCES country (iso_2),
    province     varchar,
    postal_code  varchar,
    phone        varchar,
    created_at   timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at   timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at   timestamp WITH TIME ZONE,
    metadata     JSONB
);


ALTER TABLE users
    ADD CONSTRAINT "FK_8abe81b9aac151ae60bf507ad15"
        FOREIGN KEY (default_billing_address_id) REFERENCES address;
ALTER TABLE users
    ADD CONSTRAINT "FK_8abe81b9aac151ae60bf507ag3"
        FOREIGN KEY (default_shipping_address_id) REFERENCES address;
CREATE INDEX IF NOT EXISTS "IDX_9c9614b2f9d01665800ea8dbff" ON address (user_id);

CREATE TABLE IF NOT EXISTS region_payment_providers
(
    region_id   varchar NOT NULL
        CONSTRAINT "FK_8aaa78ba90d3802edac317df869" REFERENCES region ON DELETE CASCADE,
    provider_id varchar NOT NULL
        CONSTRAINT "FK_3a6947180aeec283cd92c59ebb0" REFERENCES payment_provider ON DELETE CASCADE,
    CONSTRAINT "PK_9fa1e69914d3dd752de6b1da407" PRIMARY KEY (region_id,
                                                             provider_id)
);


CREATE INDEX IF NOT EXISTS "IDX_8aaa78ba90d3802edac317df86" ON region_payment_providers (region_id);


CREATE INDEX IF NOT EXISTS "IDX_3a6947180aeec283cd92c59ebb" ON region_payment_providers (provider_id);


CREATE TABLE IF NOT EXISTS region_fulfillment_providers
(
    region_id   varchar NOT NULL
        CONSTRAINT "FK_c556e14eff4d6f03db593df955e" REFERENCES region ON DELETE CASCADE,
    provider_id varchar NOT NULL
        CONSTRAINT "FK_37f361c38a18d12a3fa3158d0cf" REFERENCES fulfillment_provider ON DELETE CASCADE,
    CONSTRAINT "PK_5b7d928a1fb50d6803868cfab3a" PRIMARY KEY (region_id,
                                                             provider_id)
);


CREATE INDEX IF NOT EXISTS "IDX_c556e14eff4d6f03db593df955" ON region_fulfillment_providers (region_id);


CREATE INDEX IF NOT EXISTS "IDX_37f361c38a18d12a3fa3158d0c" ON region_fulfillment_providers (provider_id);


CREATE TABLE IF NOT EXISTS discount_regions
(
    discount_id varchar NOT NULL
        CONSTRAINT "FK_f4194aa81073f3fab8aa86906ff" REFERENCES discount ON DELETE CASCADE,
    region_id   varchar NOT NULL
        CONSTRAINT "FK_a21a7ffbe420d492eb46c305fec" REFERENCES region ON DELETE CASCADE,
    CONSTRAINT "PK_15974566a8b6e04a7c754e85b75" PRIMARY KEY (discount_id,
                                                             region_id)
);


CREATE INDEX IF NOT EXISTS "IDX_f4194aa81073f3fab8aa86906f" ON discount_regions (discount_id);


CREATE INDEX IF NOT EXISTS "IDX_a21a7ffbe420d492eb46c305fe" ON discount_regions (region_id);


CREATE TABLE IF NOT EXISTS tax_rate
(
    id         varchar                                NOT NULL
        CONSTRAINT "PK_23b71b53f650c0b39e99ccef4fd" PRIMARY KEY,
    rate       real,
    code       varchar,
    name       varchar                                NOT NULL,
    region_id  varchar                                NOT NULL
        CONSTRAINT "FK_b95a1e03b051993d208366cb960" REFERENCES region,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata   JSONB
);


CREATE TABLE IF NOT EXISTS product_tax_rate
(
    product_id varchar                                NOT NULL
        CONSTRAINT "FK_1d04aebeabb6a89f87e536a124d" REFERENCES product ON DELETE CASCADE,
    rate_id    varchar                                NOT NULL
        CONSTRAINT "FK_2484cf14c437a04586b07e7dddb" REFERENCES tax_rate ON DELETE CASCADE,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata   JSONB,
    CONSTRAINT "PK_326257ce468df46cd5c8c5922e9" PRIMARY KEY (product_id,
                                                             rate_id)
);


CREATE INDEX IF NOT EXISTS "IDX_2484cf14c437a04586b07e7ddd" ON product_tax_rate (rate_id);


CREATE INDEX IF NOT EXISTS "IDX_1d04aebeabb6a89f87e536a124" ON product_tax_rate (product_id);


CREATE TABLE IF NOT EXISTS product_type_tax_rate
(
    product_type_id varchar                                NOT NULL
        CONSTRAINT "FK_25a3138bb236f63d9bb6c8ff111" REFERENCES product_type ON DELETE CASCADE,
    rate_id         varchar                                NOT NULL
        CONSTRAINT "FK_ece65a774192b34253abc4cd672" REFERENCES tax_rate ON DELETE CASCADE,
    created_at      timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at      timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata        JSONB,
    CONSTRAINT "PK_ddc9242de1d99bc7674969289f0" PRIMARY KEY (product_type_id,
                                                             rate_id)
);


CREATE INDEX IF NOT EXISTS "IDX_ece65a774192b34253abc4cd67" ON product_type_tax_rate (rate_id);


CREATE INDEX IF NOT EXISTS "IDX_25a3138bb236f63d9bb6c8ff11" ON product_type_tax_rate (product_type_id);


CREATE TABLE IF NOT EXISTS shipping_tax_rate
(
    shipping_option_id varchar                                NOT NULL
        CONSTRAINT "FK_f672727ab020df6c50fb64c1a70" REFERENCES shipping_option ON DELETE CASCADE,
    rate_id            varchar                                NOT NULL
        CONSTRAINT "FK_346e0016cf045b9980747747645" REFERENCES tax_rate ON DELETE CASCADE,
    created_at         timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at         timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata           JSONB,
    CONSTRAINT "PK_bcd93b14d7e2695365d383f5eae" PRIMARY KEY (shipping_option_id,
                                                             rate_id)
);

CREATE INDEX IF NOT EXISTS "IDX_346e0016cf045b998074774764" ON shipping_tax_rate (rate_id);
CREATE INDEX IF NOT EXISTS "IDX_f672727ab020df6c50fb64c1a7" ON shipping_tax_rate (shipping_option_id);


CREATE TABLE IF NOT EXISTS customer_group
(
    id         varchar                                NOT NULL
        CONSTRAINT "PK_88e7da3ff7262d9e0a35aa3664e" PRIMARY KEY,
    name       varchar                                NOT NULL,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at timestamp WITH TIME ZONE,
    metadata   JSONB
);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_c4c3a5225a7a1f0af782c40abc" ON customer_group (name)
    WHERE (deleted_at IS NULL);


CREATE TABLE IF NOT EXISTS customer_group_customers
(
    customer_group_id varchar NOT NULL
        CONSTRAINT "FK_620330964db8d2999e67b0dbe3e" REFERENCES customer_group ON DELETE CASCADE,
    user_id           varchar NOT NULL
        CONSTRAINT "FK_3c6412d076292f439269abe1a23" REFERENCES users ON DELETE CASCADE,
    CONSTRAINT "PK_e28a55e34ad1e2d3df9a0ac86d3" PRIMARY KEY (customer_group_id,
                                                             user_id)
);

CREATE INDEX IF NOT EXISTS "IDX_620330964db8d2999e67b0dbe3" ON customer_group_customers (customer_group_id);
CREATE INDEX IF NOT EXISTS "IDX_3c6412d076292f439269abe1a2" ON customer_group_customers (user_id);


create type DISCOUNT_CONDITION_TYPE as enum ('PRODUCTS', 'PRODUCT_TYPES', 'PRODUCT_COLLECTIONS', 'PRODUCT_TAGS', 'CUSTOMER_GROUPS');
create type DISCOUNT_CONDITION_OPERATOR_TYPE as enum ('IN', 'NOT_IN');
CREATE TABLE IF NOT EXISTS discount_condition
(
    id               varchar                                NOT NULL
        CONSTRAINT "PK_e6b81d83133ddc21a2baf2e2204" PRIMARY KEY,
    TYPE             DISCOUNT_CONDITION_TYPE                NOT NULL,
    OPERATOR         DISCOUNT_CONDITION_OPERATOR_TYPE       NOT NULL,
    discount_rule_id varchar                                NOT NULL
        CONSTRAINT "FK_efff700651718e452ca9580a624" REFERENCES discount_rule,
    created_at       timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at       timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at       timestamp WITH TIME ZONE,
    metadata         JSONB,
    CONSTRAINT dctypeuniq UNIQUE (TYPE,
                                  OPERATOR,
                                  discount_rule_id)
);


CREATE INDEX IF NOT EXISTS "IDX_efff700651718e452ca9580a62" ON discount_condition (discount_rule_id);


CREATE TABLE IF NOT EXISTS discount_condition_customer_group
(
    customer_group_id varchar                                NOT NULL
        CONSTRAINT "FK_4d5f98645a67545d8dea42e2eb8" REFERENCES customer_group ON DELETE CASCADE,
    condition_id      varchar                                NOT NULL
        CONSTRAINT "FK_8486ee16e69013c645d0b8716b6" REFERENCES discount_condition ON DELETE CASCADE,
    created_at        timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at        timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata          JSONB,
    CONSTRAINT "PK_cdc8b2277169a16b8b7d4c73e0e" PRIMARY KEY (customer_group_id,
                                                             condition_id)
);


CREATE INDEX IF NOT EXISTS "IDX_8486ee16e69013c645d0b8716b" ON discount_condition_customer_group (condition_id);


CREATE INDEX IF NOT EXISTS "IDX_4d5f98645a67545d8dea42e2eb" ON discount_condition_customer_group (customer_group_id);


CREATE TABLE IF NOT EXISTS discount_condition_product_collection
(
    product_collection_id varchar                                NOT NULL
        CONSTRAINT "FK_a0b05dc4257abe639cb75f8eae2" REFERENCES product_collection ON DELETE CASCADE,
    condition_id          varchar                                NOT NULL
        CONSTRAINT "FK_a1c4f9cfb599ad1f0db39cadd5f" REFERENCES discount_condition ON DELETE CASCADE,
    created_at            timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at            timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata              JSONB,
    CONSTRAINT "PK_b3508fc787aa4a38705866cbb6d" PRIMARY KEY (product_collection_id,
                                                             condition_id)
);


CREATE INDEX IF NOT EXISTS "IDX_a1c4f9cfb599ad1f0db39cadd5" ON discount_condition_product_collection (condition_id);


CREATE INDEX IF NOT EXISTS "IDX_a0b05dc4257abe639cb75f8eae" ON discount_condition_product_collection (product_collection_id);


CREATE TABLE IF NOT EXISTS discount_condition_product_tag
(
    product_tag_id varchar                                NOT NULL
        CONSTRAINT "FK_01486cc9dc6b36bf658685535f6" REFERENCES product_tag ON DELETE CASCADE,
    condition_id   varchar                                NOT NULL
        CONSTRAINT "FK_fbb2499551ed074526f3ee36241" REFERENCES discount_condition ON DELETE CASCADE,
    created_at     timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at     timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata       JSONB,
    CONSTRAINT "PK_a95382c1e62205b121aa058682b" PRIMARY KEY (product_tag_id,
                                                             condition_id)
);


CREATE INDEX IF NOT EXISTS "IDX_fbb2499551ed074526f3ee3624" ON discount_condition_product_tag (condition_id);


CREATE INDEX IF NOT EXISTS "IDX_01486cc9dc6b36bf658685535f" ON discount_condition_product_tag (product_tag_id);


CREATE TABLE IF NOT EXISTS discount_condition_product_type
(
    product_type_id varchar                                NOT NULL
        CONSTRAINT "FK_e706deb68f52ab2756119b9e704" REFERENCES product_type ON DELETE CASCADE,
    condition_id    varchar                                NOT NULL
        CONSTRAINT "FK_6ef23ce0b1d9cf9b5b833e52b9d" REFERENCES discount_condition ON DELETE CASCADE,
    created_at      timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at      timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata        JSONB,
    CONSTRAINT "PK_35d538a5a24399d0df978df12ed" PRIMARY KEY (product_type_id,
                                                             condition_id)
);


CREATE INDEX IF NOT EXISTS "IDX_6ef23ce0b1d9cf9b5b833e52b9" ON discount_condition_product_type (condition_id);


CREATE INDEX IF NOT EXISTS "IDX_e706deb68f52ab2756119b9e70" ON discount_condition_product_type (product_type_id);


CREATE TABLE IF NOT EXISTS discount_condition_product
(
    product_id   varchar                                NOT NULL
        CONSTRAINT "FK_c759f53b2e48e8cfb50638fe4e0" REFERENCES product ON DELETE CASCADE,
    condition_id varchar                                NOT NULL
        CONSTRAINT "FK_f05132301e95bdab4ba1cf29a24" REFERENCES discount_condition ON DELETE CASCADE,
    created_at   timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at   timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata     JSONB,
    CONSTRAINT "PK_994eb4529fdbf14450d64ec17e8" PRIMARY KEY (product_id,
                                                             condition_id)
);


CREATE INDEX IF NOT EXISTS "IDX_f05132301e95bdab4ba1cf29a2" ON discount_condition_product (condition_id);


CREATE INDEX IF NOT EXISTS "IDX_c759f53b2e48e8cfb50638fe4e" ON discount_condition_product (product_id);


create type PRICE_LIST_TYPE as enum ('SALE', 'OVERRIDE');
create type PRICE_LIST_STATUS_TYPE as enum ('ACTIVE', 'DRAFT');
CREATE TABLE IF NOT EXISTS price_list
(
    id          varchar                                                          NOT NULL
        CONSTRAINT "PK_52ea7826468b1c889cb2c28df03" PRIMARY KEY,
    name        varchar                                                          NOT NULL,
    description varchar                                                          NOT NULL,
    TYPE        PRICE_LIST_TYPE          DEFAULT 'SALE'::PRICE_LIST_TYPE         NOT NULL,
    status      PRICE_LIST_STATUS_TYPE   DEFAULT 'DRAFT'::PRICE_LIST_STATUS_TYPE NOT NULL,
    starts_at   timestamp WITH TIME ZONE,
    ends_at     timestamp WITH TIME ZONE,
    created_at  timestamp WITH TIME ZONE DEFAULT now()                           NOT NULL,
    updated_at  timestamp WITH TIME ZONE DEFAULT now()                           NOT NULL,
    deleted_at  timestamp WITH TIME ZONE
);


CREATE TABLE IF NOT EXISTS money_amount
(
    id            varchar                                NOT NULL
        CONSTRAINT "PK_022e49a7e21a8dfb820f788778a" PRIMARY KEY,
    currency_code varchar                                NOT NULL
        CONSTRAINT "FK_e15811f81339e4bd8c440aebe1c" REFERENCES currency,
    amount        integer                                NOT NULL,
    region_id     varchar
        CONSTRAINT "FK_b433e27b7a83e6d12ab26b15b03" REFERENCES region,
    created_at    timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at    timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at    timestamp WITH TIME ZONE,
    min_quantity  integer,
    max_quantity  integer,
    price_list_id varchar
        CONSTRAINT "FK_f249976b079375499662eb80c40" REFERENCES price_list ON DELETE CASCADE
);


CREATE INDEX IF NOT EXISTS "IDX_money_amount_currency_code" ON money_amount (currency_code);


CREATE INDEX IF NOT EXISTS idx_money_amount_region_id ON money_amount (region_id);


CREATE TABLE IF NOT EXISTS price_list_customer_groups
(
    price_list_id     varchar NOT NULL
        CONSTRAINT "FK_52875734e9dd69064f0041f4d92" REFERENCES price_list ON UPDATE CASCADE ON DELETE CASCADE,
    customer_group_id varchar NOT NULL
        CONSTRAINT "FK_c5516f550433c9b1c2630d787a7" REFERENCES customer_group ON DELETE CASCADE,
    CONSTRAINT "PK_1afcbe15cc8782dc80c05707df9" PRIMARY KEY (price_list_id,
                                                             customer_group_id)
);

CREATE INDEX IF NOT EXISTS "IDX_52875734e9dd69064f0041f4d9" ON price_list_customer_groups (price_list_id);
CREATE INDEX IF NOT EXISTS "IDX_c5516f550433c9b1c2630d787a" ON price_list_customer_groups (customer_group_id);


CREATE TABLE IF NOT EXISTS sales_channel
(
    id          varchar                                NOT NULL
        CONSTRAINT "PK_d1eb0b923ea5a0eb1e0916191f1" PRIMARY KEY,
    created_at  timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at  timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at  timestamp WITH TIME ZONE,
    name        varchar                                NOT NULL,
    description varchar,
    is_disabled boolean                  DEFAULT FALSE NOT NULL,
    metadata    JSONB
);


create type CART_TYPE as enum ('DEFAULT', 'SWAP', 'DRAFT_ORDER', 'PAYMENT_LINK', 'CLAIM');
CREATE TABLE IF NOT EXISTS cart
(
    id                    varchar                                               NOT NULL
        CONSTRAINT "PK_c524ec48751b9b5bcfbf6e59be7" PRIMARY KEY,
    email                 varchar,
    billing_address_id    varchar
        CONSTRAINT "FK_6b9c66b5e36f7c827dfaa092f94" REFERENCES address,
    shipping_address_id   varchar
        CONSTRAINT "FK_ced15a9a695d2b5db9dabce763d" REFERENCES address,
    region_id             varchar                                               NOT NULL
        CONSTRAINT "FK_484c329f4783be4e18e5e2ff090" REFERENCES region,
    user_id               varchar
        CONSTRAINT "FK_242205c81c1152fab1b6e848470" REFERENCES users,
    payment_id            varchar
        CONSTRAINT "REL_9d1a161434c610aae7c3df2dc7" UNIQUE
        CONSTRAINT "FK_9d1a161434c610aae7c3df2dc7e" REFERENCES payment,
    TYPE                  CART_TYPE                DEFAULT 'DEFAULT'::CART_TYPE NOT NULL,
    completed_at          timestamp WITH TIME ZONE,
    created_at            timestamp WITH TIME ZONE DEFAULT now()                NOT NULL,
    updated_at            timestamp WITH TIME ZONE DEFAULT now()                NOT NULL,
    deleted_at            timestamp WITH TIME ZONE,
    metadata              JSONB,
    idempotency_key       varchar,
    context               JSONB,
    payment_authorized_at timestamp WITH TIME ZONE,
    sales_channel_id      varchar
        CONSTRAINT "FK_a2bd3c26f42e754b9249ba78fd6" REFERENCES sales_channel
);

CREATE INDEX IF NOT EXISTS "IDX_6b9c66b5e36f7c827dfaa092f9" ON cart (billing_address_id);
CREATE INDEX IF NOT EXISTS "IDX_ced15a9a695d2b5db9dabce763" ON cart (shipping_address_id);
CREATE INDEX IF NOT EXISTS "IDX_484c329f4783be4e18e5e2ff09" ON cart (region_id);
CREATE INDEX IF NOT EXISTS "IDX_242205c81c1152fab1b6e84847" ON cart (user_id);
CREATE INDEX IF NOT EXISTS "IDX_9d1a161434c610aae7c3df2dc7" ON cart (payment_id);

create type PAYMENT_SESSION_STATUS_TYPE as enum ('AUTHORIZED', 'PENDING', 'REQUIRES_MORE', 'ERROR', 'CANCELED');
CREATE TABLE IF NOT EXISTS payment_session
(
    id                    varchar                                NOT NULL
        CONSTRAINT "PK_a1a91b20f7f3b1e5afb5485cbcd" PRIMARY KEY,
    cart_id               varchar
        CONSTRAINT "FK_d25ba0787e1510ddc5d442ebcfa" REFERENCES cart,
    provider_id           varchar                                NOT NULL,
    is_selected           boolean,
    status                PAYMENT_SESSION_STATUS_TYPE            NOT NULL,
    DATA                  JSONB                                  NOT NULL,
    created_at            timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at            timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    idempotency_key       varchar,
    payment_authorized_at timestamp WITH TIME ZONE,
    amount                integer,
    is_initiated          boolean                  DEFAULT FALSE NOT NULL,
    CONSTRAINT "OneSelected" UNIQUE (cart_id,
                                     is_selected)
);

CREATE INDEX IF NOT EXISTS "IDX_d25ba0787e1510ddc5d442ebcf" ON payment_session (cart_id);
CREATE INDEX IF NOT EXISTS "IDX_d18ad72f2fb7c87f075825b6f8" ON payment_session (provider_id);
CREATE UNIQUE INDEX IF NOT EXISTS "UniqPaymentSessionCartIdProviderId" ON payment_session (cart_id, provider_id)
    WHERE (cart_id IS NOT NULL);
ALTER TABLE payment
    ADD CONSTRAINT "FK_4665f17abc1e81dd58330e58542"
        FOREIGN KEY (cart_id) REFERENCES cart;


create type ORDER_PAYMENT_STATUS_TYPE as enum (
    'NOT_PAID', 'WAITING', 'CAPTURED', 'PARTIALLY_REFUNDED', 'REFUNDED', 'CANCELED', 'REQUIRES_ACTION');
create type ORDER_FULFILLMENT_STATUS_TYPE as enum (
    'NOT_FULFILLED', 'PARTIALLY_REFUNDED', 'FULFILLED', 'PARTIALLY_SHIPPED', 'SHIPPED',
    'PARTIALLY_RETURNED', 'RETURNED', 'CANCELED', 'REQUIRES_ACTION' );
create type ORDER_STATUS_TYPE as enum (
    'PENDING', 'COMPLETED', 'ARCHIVED', 'CANCELED', 'REQUIRES_ACTION');

CREATE TABLE IF NOT EXISTS "order"
(
    id                  varchar                                                                              NOT NULL
        CONSTRAINT "PK_1031171c13130102495201e3e20" PRIMARY KEY,
    status              ORDER_STATUS_TYPE             DEFAULT 'PENDING'::ORDER_STATUS_TYPE                   NOT NULL,
    fulfillment_status  ORDER_FULFILLMENT_STATUS_TYPE DEFAULT 'NOT_FULFILLED'::ORDER_FULFILLMENT_STATUS_TYPE NOT NULL,
    payment_status      ORDER_PAYMENT_STATUS_TYPE     DEFAULT 'NOT_PAID'::ORDER_PAYMENT_STATUS_TYPE          NOT NULL,
    display_id          serial,
    cart_id             varchar
        CONSTRAINT "REL_c99a206eb11ad45f6b7f04f2dc" UNIQUE
        CONSTRAINT "FK_c99a206eb11ad45f6b7f04f2dcc" REFERENCES cart,
    user_id             varchar                                                                              NOT NULL
        CONSTRAINT "FK_cd7812c96209c5bdd48a6b858b0" REFERENCES users,
    email               varchar                                                                              NOT NULL,
    billing_address_id  varchar
        CONSTRAINT "FK_5568d3b9ce9f7abeeb37511ecf2" REFERENCES address,
    shipping_address_id varchar
        CONSTRAINT "FK_19b0c6293443d1b464f604c3316" REFERENCES address,
    region_id           varchar                                                                              NOT NULL
        CONSTRAINT "FK_e1fcce2b18dbcdbe0a5ba9a68b8" REFERENCES region,
    currency_code       varchar                                                                              NOT NULL
        CONSTRAINT "FK_717a141f96b76d794d409f38129" REFERENCES currency,
    tax_rate            real,
    canceled_at         timestamp WITH TIME ZONE,
    created_at          timestamp WITH TIME ZONE      DEFAULT now()                                          NOT NULL,
    updated_at          timestamp WITH TIME ZONE      DEFAULT now()                                          NOT NULL,
    metadata            JSONB,
    idempotency_key     varchar,
    draft_order_id      varchar
        CONSTRAINT "UQ_727b872f86c7378474a8fa46147" UNIQUE,
    no_notification     boolean,
    external_id         varchar,
    sales_channel_id    varchar
        CONSTRAINT "FK_6ff7e874f01b478c115fdd462eb" REFERENCES sales_channel
);


ALTER TABLE payment
    ADD CONSTRAINT "FK_f5221735ace059250daac9d9803"
        FOREIGN KEY (order_id) REFERENCES "order";


CREATE TABLE IF NOT EXISTS gift_card
(
    id          varchar                                NOT NULL
        CONSTRAINT "PK_af4e338d2d41035042843ad641f" PRIMARY KEY,
    code        varchar                                NOT NULL,
    value       integer                                NOT NULL,
    balance     integer                                NOT NULL,
    region_id   varchar                                NOT NULL
        CONSTRAINT "FK_b6bcf8c3903097b84e85154eed3" REFERENCES region,
    order_id    varchar
        CONSTRAINT "FK_dfc1f02bb0552e79076aa58dbb0" REFERENCES "order",
    is_disabled boolean                  DEFAULT FALSE NOT NULL,
    ends_at     timestamp WITH TIME ZONE,
    created_at  timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at  timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at  timestamp WITH TIME ZONE,
    metadata    JSONB,
    tax_rate    real
);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_53cb5605fa42e82b4d47b47bda" ON gift_card (code);


CREATE INDEX IF NOT EXISTS "IDX_b6bcf8c3903097b84e85154eed" ON gift_card (region_id);


CREATE INDEX IF NOT EXISTS "IDX_dfc1f02bb0552e79076aa58dbb" ON gift_card (order_id);


create type SWAP_PAYMENT_STATUS_TYPE as enum (
    'NOT_PAID', 'AWAITING', 'CAPTURED', 'CONFIRMED', 'CANCELED', 'DIFFERENCE_REFUNDED',
    'PARTIALLY_REFUNDED', 'REFUNDED', 'REQUIRES_ACTION');
create type SWAP_FULFILLMENT_STATUS_TYPE as enum (
    'NOT_FULFILLED', 'FULFILLED', 'SHIPPED', 'PARTIALLY_SHIPPED', 'CANCELLED', 'REQUIRES_ACTION');
CREATE TABLE IF NOT EXISTS swap
(
    id                  varchar                                NOT NULL
        CONSTRAINT "PK_4a10d0f359339acef77e7f986d9" PRIMARY KEY,
    fulfillment_status  SWAP_FULFILLMENT_STATUS_TYPE           NOT NULL,
    payment_status      SWAP_PAYMENT_STATUS_TYPE               NOT NULL,
    order_id            varchar                                NOT NULL
        CONSTRAINT "FK_52dd74e8c989aa5665ad2852b8b" REFERENCES "order",
    difference_due      integer,
    shipping_address_id varchar
        CONSTRAINT "FK_f5189d38b3d3bd496618bf54c57" REFERENCES address,
    cart_id             varchar
        CONSTRAINT "REL_402e8182bc553e082f6380020b" UNIQUE
        CONSTRAINT "FK_402e8182bc553e082f6380020b4" REFERENCES cart,
    confirmed_at        timestamp WITH TIME ZONE,
    created_at          timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at          timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at          timestamp WITH TIME ZONE,
    metadata            JSONB,
    idempotency_key     varchar,
    no_notification     boolean,
    canceled_at         timestamp WITH TIME ZONE,
    allow_backorder     boolean                  DEFAULT FALSE NOT NULL
);


ALTER TABLE payment
    ADD CONSTRAINT "FK_c17aff091441b7c25ec3d68d36c"
        FOREIGN KEY (swap_id) REFERENCES swap;


CREATE INDEX IF NOT EXISTS "IDX_52dd74e8c989aa5665ad2852b8" ON swap (order_id);


CREATE TABLE IF NOT EXISTS gift_card_transaction
(
    id           varchar                                NOT NULL
        CONSTRAINT "PK_cfb5b4ba5447a507aef87d73fe7" PRIMARY KEY,
    gift_card_id varchar                                NOT NULL
        CONSTRAINT "FK_3ff5597f1d7e02bba41541846f4" REFERENCES gift_card,
    order_id     varchar                                NOT NULL
        CONSTRAINT "FK_d7d441b81012f87d4265fa57d24" REFERENCES "order",
    amount       integer                                NOT NULL,
    created_at   timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    is_taxable   boolean,
    tax_rate     real,
    CONSTRAINT gcuniq UNIQUE (gift_card_id,
                              order_id)
);

CREATE INDEX IF NOT EXISTS "IDX_d7d441b81012f87d4265fa57d2" ON gift_card_transaction (order_id);


create type REFUND_REASON_TYPE as enum ('DISCOUNT', 'RETURN', 'SWAP', 'CLAIM', 'OTHER');
CREATE TABLE IF NOT EXISTS refund
(
    id              varchar                                NOT NULL
        CONSTRAINT "PK_f1cefa2e60d99b206c46c1116e5" PRIMARY KEY,
    order_id        varchar
        CONSTRAINT "FK_eec9d9af4ca098e19ea6b499eaa" REFERENCES "order",
    amount          integer                                NOT NULL,
    note            varchar,
    reason          REFUND_REASON_TYPE                     NOT NULL,
    created_at      timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at      timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata        JSONB,
    idempotency_key varchar,
    payment_id      varchar
        CONSTRAINT "FK_refund_payment_id" REFERENCES payment
);

CREATE INDEX IF NOT EXISTS "IDX_eec9d9af4ca098e19ea6b499ea" ON refund (order_id);
CREATE INDEX IF NOT EXISTS "IDX_refund_payment_id" ON refund (payment_id);
CREATE INDEX IF NOT EXISTS "IDX_579e01fb94f4f58db480857e05" ON "order" (display_id);
CREATE INDEX IF NOT EXISTS "IDX_c99a206eb11ad45f6b7f04f2dc" ON "order" (cart_id);
CREATE INDEX IF NOT EXISTS "IDX_cd7812c96209c5bdd48a6b858b" ON "order" (user_id);
CREATE INDEX IF NOT EXISTS "IDX_5568d3b9ce9f7abeeb37511ecf" ON "order" (billing_address_id);
CREATE INDEX IF NOT EXISTS "IDX_19b0c6293443d1b464f604c331" ON "order" (shipping_address_id);
CREATE INDEX IF NOT EXISTS "IDX_e1fcce2b18dbcdbe0a5ba9a68b" ON "order" (region_id);
CREATE INDEX IF NOT EXISTS "IDX_order_currency_code" ON "order" (currency_code);


CREATE TABLE IF NOT EXISTS store
(
    id                       varchar                                                            NOT NULL
        CONSTRAINT "PK_f3172007d4de5ae8e7692759d79" PRIMARY KEY,
    name                     varchar                  DEFAULT 'Crassus Store'::CHARACTER NOT NULL,
    default_currency_code    varchar                  DEFAULT 'usd'::CHARACTER          NOT NULL
        CONSTRAINT "FK_55beebaa09e947cccca554af222" REFERENCES currency,
    swap_link_template       varchar,
    created_at               timestamp WITH TIME ZONE DEFAULT now()                             NOT NULL,
    updated_at               timestamp WITH TIME ZONE DEFAULT now()                             NOT NULL,
    metadata                 JSONB,
    payment_link_template    varchar,
    invite_link_template     varchar,
    default_sales_channel_id varchar
        CONSTRAINT "UQ_61b0f48cccbb5f41c750bac7286" UNIQUE
        CONSTRAINT "FK_61b0f48cccbb5f41c750bac7286" REFERENCES sales_channel,
    default_location_id      varchar
);


CREATE TABLE IF NOT EXISTS cart_discounts
(
    cart_id     varchar NOT NULL
        CONSTRAINT "FK_6680319ebe1f46d18f106191d59" REFERENCES cart ON DELETE CASCADE,
    discount_id varchar NOT NULL
        CONSTRAINT "FK_8df75ef4f35f217768dc1135458" REFERENCES discount ON DELETE CASCADE,
    CONSTRAINT "PK_10bd412c9071ccc0cf555afd9bb" PRIMARY KEY (cart_id,
                                                             discount_id)
);


CREATE INDEX IF NOT EXISTS "IDX_6680319ebe1f46d18f106191d5" ON cart_discounts (cart_id);


CREATE INDEX IF NOT EXISTS "IDX_8df75ef4f35f217768dc113545" ON cart_discounts (discount_id);


CREATE TABLE IF NOT EXISTS cart_gift_cards
(
    cart_id      varchar NOT NULL
        CONSTRAINT "FK_d38047a90f3d42f0be7909e8aea" REFERENCES cart ON DELETE CASCADE,
    gift_card_id varchar NOT NULL
        CONSTRAINT "FK_0fb38b6d167793192bc126d835e" REFERENCES gift_card ON DELETE CASCADE,
    CONSTRAINT "PK_2389be82bf0ef3635e2014c9ef1" PRIMARY KEY (cart_id,
                                                             gift_card_id)
);


CREATE INDEX IF NOT EXISTS "IDX_d38047a90f3d42f0be7909e8ae" ON cart_gift_cards (cart_id);


CREATE INDEX IF NOT EXISTS "IDX_0fb38b6d167793192bc126d835" ON cart_gift_cards (gift_card_id);


CREATE TABLE IF NOT EXISTS order_discounts
(
    order_id    varchar NOT NULL
        CONSTRAINT "FK_e7b488cebe333f449398769b2cc" REFERENCES "order" ON DELETE CASCADE,
    discount_id varchar NOT NULL
        CONSTRAINT "FK_0fc1ec4e3db9001ad60c19daf16" REFERENCES discount ON DELETE CASCADE,
    CONSTRAINT "PK_a7418714ffceebc125bf6d8fcfe" PRIMARY KEY (order_id,
                                                             discount_id)
);


CREATE INDEX IF NOT EXISTS "IDX_e7b488cebe333f449398769b2c" ON order_discounts (order_id);


CREATE INDEX IF NOT EXISTS "IDX_0fc1ec4e3db9001ad60c19daf1" ON order_discounts (discount_id);


CREATE TABLE IF NOT EXISTS order_gift_cards
(
    order_id     varchar NOT NULL
        CONSTRAINT "FK_e62ff11e4730bb3adfead979ee2" REFERENCES "order" ON DELETE CASCADE,
    gift_card_id varchar NOT NULL
        CONSTRAINT "FK_f2bb9f71e95b315eb24b2b84cb3" REFERENCES gift_card ON DELETE CASCADE,
    CONSTRAINT "PK_49a8ec66a6625d7c2e3526e05b4" PRIMARY KEY (order_id,
                                                             gift_card_id)
);


CREATE INDEX IF NOT EXISTS "IDX_e62ff11e4730bb3adfead979ee" ON order_gift_cards (order_id);


CREATE INDEX IF NOT EXISTS "IDX_f2bb9f71e95b315eb24b2b84cb" ON order_gift_cards (gift_card_id);


CREATE TABLE IF NOT EXISTS store_currencies
(
    store_id      varchar NOT NULL
        CONSTRAINT "FK_b4f4b63d1736689b7008980394c" REFERENCES store ON DELETE CASCADE,
    currency_code varchar NOT NULL
        CONSTRAINT "FK_82a6bbb0b527c20a0002ddcbd60" REFERENCES currency ON DELETE CASCADE,
    CONSTRAINT "PK_0f2bff3bccc785c320a4df836de" PRIMARY KEY (store_id,
                                                             currency_code)
);

CREATE INDEX IF NOT EXISTS "IDX_b4f4b63d1736689b7008980394" ON store_currencies (store_id);
CREATE INDEX IF NOT EXISTS "IDX_82a6bbb0b527c20a0002ddcbd6" ON store_currencies (currency_code);


create type CLAIM_ORDER_TYPE as enum ('REFUND', 'REPLACE');
create type CLAIM_ORDER_FULFILLMENT_STATUS_TYPE as enum ('NOT_FULFILLED', 'PARTIALLY_FULFILLED', 'FULFILLED',
    'PARTIALLY_SHIPPED', 'SHIPPED', 'PARTIALLY_RETURNED', 'RETURNED', 'CANCELLED', 'REQUIRES_ACTION');
create type CLAIM_ORDER_PAYMENT_STATUS_TYPE as enum ('NA', 'NOT_REFUNDED', 'REFUNDED');

CREATE TABLE IF NOT EXISTS claim_order
(
    id                  varchar                                                                                          NOT NULL
        CONSTRAINT "PK_8981f5595a4424021466aa4c7a4" PRIMARY KEY,
    payment_status      CLAIM_ORDER_PAYMENT_STATUS_TYPE     DEFAULT 'NA'::CLAIM_ORDER_PAYMENT_STATUS_TYPE                NOT NULL,
    fulfillment_status  CLAIM_ORDER_FULFILLMENT_STATUS_TYPE DEFAULT 'NOT_FULFILLED'::CLAIM_ORDER_FULFILLMENT_STATUS_TYPE NOT NULL,
    TYPE                CLAIM_ORDER_TYPE                                                                                 NOT NULL,
    order_id            varchar                                                                                          NOT NULL
        CONSTRAINT "FK_f49e3974465d3c3a33d449d3f31" REFERENCES "order",
    shipping_address_id varchar
        CONSTRAINT "FK_017d58bf8260c6e1a2588d258e2" REFERENCES address,
    refund_amount       integer,
    canceled_at         timestamp WITH TIME ZONE,
    created_at          timestamp WITH TIME ZONE            DEFAULT now()                                                NOT NULL,
    updated_at          timestamp WITH TIME ZONE            DEFAULT now()                                                NOT NULL,
    deleted_at          timestamp WITH TIME ZONE,
    metadata            JSONB,
    idempotency_key     varchar,
    no_notification     boolean
);


CREATE TABLE IF NOT EXISTS fulfillment
(
    id               varchar                                      NOT NULL
        CONSTRAINT "PK_50c102da132afffae660585981f" PRIMARY KEY,
    swap_id          varchar
        CONSTRAINT "FK_a52e234f729db789cf473297a5c" REFERENCES swap,
    order_id         varchar
        CONSTRAINT "FK_f129acc85e346a10eed12b86fca" REFERENCES "order",
    tracking_numbers JSONB                    DEFAULT '[]'::JSONB NOT NULL,
    DATA             JSONB                                        NOT NULL,
    shipped_at       timestamp WITH TIME ZONE,
    canceled_at      timestamp WITH TIME ZONE,
    created_at       timestamp WITH TIME ZONE DEFAULT now()       NOT NULL,
    updated_at       timestamp WITH TIME ZONE DEFAULT now()       NOT NULL,
    metadata         JSONB,
    idempotency_key  varchar,
    provider_id      varchar
        CONSTRAINT "FK_beb35a6de60a6c4f91d5ae57e44" REFERENCES fulfillment_provider,
    claim_order_id   varchar
        CONSTRAINT "FK_d73e55964e0ff2db8f03807d52e" REFERENCES claim_order,
    no_notification  boolean,
    location_id      varchar
);

CREATE INDEX IF NOT EXISTS "IDX_d73e55964e0ff2db8f03807d52" ON fulfillment (claim_order_id);
CREATE INDEX IF NOT EXISTS "IDX_a52e234f729db789cf473297a5" ON fulfillment (swap_id);
CREATE INDEX IF NOT EXISTS "IDX_f129acc85e346a10eed12b86fc" ON fulfillment (order_id);
CREATE INDEX IF NOT EXISTS "IDX_beb35a6de60a6c4f91d5ae57e4" ON fulfillment (provider_id);


create type RETURN_STATUS_TYPE as enum ('REQUESTED', 'RECEIVED', 'REQUIRES_ACTION', 'CANCELLED');

CREATE TABLE IF NOT EXISTS RETURN
(
    id              varchar                                                          NOT NULL
        CONSTRAINT "PK_c8ad68d13e76d75d803b5aeebc4" PRIMARY KEY,
    status          RETURN_STATUS_TYPE       DEFAULT 'REQUESTED'::RETURN_STATUS_TYPE NOT NULL,
    swap_id         varchar
        CONSTRAINT "REL_bad82d7bff2b08b87094bfac3d" UNIQUE
        CONSTRAINT "FK_bad82d7bff2b08b87094bfac3d6" REFERENCES swap,
    order_id        varchar
        CONSTRAINT "FK_d4bd17f918fc6c332b74a368c36" REFERENCES "order",
    shipping_data   JSONB,
    refund_amount   integer                                                          NOT NULL,
    received_at     timestamp WITH TIME ZONE,
    created_at      timestamp WITH TIME ZONE DEFAULT now()                           NOT NULL,
    updated_at      timestamp WITH TIME ZONE DEFAULT now()                           NOT NULL,
    metadata        JSONB,
    idempotency_key varchar,
    claim_order_id  varchar
        CONSTRAINT "UQ_71773d56eb2bacb922bc3283398" UNIQUE
        CONSTRAINT "FK_71773d56eb2bacb922bc3283398" REFERENCES claim_order,
    no_notification boolean,
    location_id     varchar
);


CREATE TABLE IF NOT EXISTS shipping_method
(
    id                 varchar NOT NULL
        CONSTRAINT "PK_b9b0adfad3c6b99229c1e7d4865" PRIMARY KEY,
    shipping_option_id varchar NOT NULL
        CONSTRAINT "FK_fc963e94854bff2714ca84cd193" REFERENCES shipping_option,
    order_id           varchar
        CONSTRAINT "FK_5267705a43d547e232535b656c2" REFERENCES "order",
    cart_id            varchar
        CONSTRAINT "FK_d92993a7d554d84571f4eea1d13" REFERENCES cart,
    swap_id            varchar
        CONSTRAINT "FK_fb94fa8d5ca940daa2a58139f86" REFERENCES swap,
    return_id          varchar
        CONSTRAINT "REL_1d9ad62038998c3a85c77a53cf" UNIQUE
        CONSTRAINT "FK_1d9ad62038998c3a85c77a53cfb" REFERENCES RETURN,
    price              integer NOT NULL
        CONSTRAINT "CHK_64c6812fe7815be30d688df513" CHECK (price >= 0),
    DATA               JSONB   NOT NULL,
    claim_order_id     varchar
        CONSTRAINT "FK_d783a66d1c91c0858752c933e68" REFERENCES claim_order,
    CONSTRAINT "CHK_a7020b08665bbd64d84a6641cf" CHECK ((claim_order_id IS NOT NULL)
        OR (order_id IS NOT NULL)
        OR (cart_id IS NOT NULL)
        OR (swap_id IS NOT NULL)
        OR (return_id IS NOT NULL))
);


CREATE INDEX IF NOT EXISTS "IDX_fc963e94854bff2714ca84cd19" ON shipping_method (shipping_option_id);
CREATE INDEX IF NOT EXISTS "IDX_5267705a43d547e232535b656c" ON shipping_method (order_id);
CREATE INDEX IF NOT EXISTS "IDX_d92993a7d554d84571f4eea1d1" ON shipping_method (cart_id);
CREATE INDEX IF NOT EXISTS "IDX_fb94fa8d5ca940daa2a58139f8" ON shipping_method (swap_id);
CREATE INDEX IF NOT EXISTS "IDX_1d9ad62038998c3a85c77a53cf" ON shipping_method (return_id);
CREATE INDEX IF NOT EXISTS "IDX_d783a66d1c91c0858752c933e6" ON shipping_method (claim_order_id);
CREATE INDEX IF NOT EXISTS "IDX_bad82d7bff2b08b87094bfac3d" ON RETURN (swap_id);
CREATE INDEX IF NOT EXISTS "IDX_71773d56eb2bacb922bc328339" ON RETURN (claim_order_id);
CREATE INDEX IF NOT EXISTS "IDX_d4bd17f918fc6c332b74a368c3" ON RETURN (order_id);
CREATE INDEX IF NOT EXISTS "IDX_f49e3974465d3c3a33d449d3f3" ON claim_order (order_id);
CREATE INDEX IF NOT EXISTS "IDX_017d58bf8260c6e1a2588d258e" ON claim_order (shipping_address_id);


create type DRAFT_ORDER_STATUS_TYPE as enum ('OPEN', 'COMPLETED');

CREATE TABLE IF NOT EXISTS draft_order
(
    id                    varchar                                                          NOT NULL
        CONSTRAINT "PK_f478946c183d98f8d88a94cfcd7" PRIMARY KEY,
    status                DRAFT_ORDER_STATUS_TYPE  DEFAULT 'OPEN'::DRAFT_ORDER_STATUS_TYPE NOT NULL,
    display_id            serial,
    cart_id               varchar
        CONSTRAINT "REL_5bd11d0e2a9628128e2c26fd0a" UNIQUE
        CONSTRAINT "FK_5bd11d0e2a9628128e2c26fd0a6" REFERENCES cart,
    order_id              varchar
        CONSTRAINT "REL_8f6dd6c49202f1466ebf21e77d" UNIQUE
        CONSTRAINT "FK_8f6dd6c49202f1466ebf21e77da" REFERENCES "order",
    canceled_at           timestamp WITH TIME ZONE,
    created_at            timestamp WITH TIME ZONE DEFAULT now()                           NOT NULL,
    updated_at            timestamp WITH TIME ZONE DEFAULT now()                           NOT NULL,
    completed_at          timestamp WITH TIME ZONE,
    metadata              JSONB,
    idempotency_key       varchar,
    no_notification_order boolean
);


ALTER TABLE "order"
    ADD CONSTRAINT "FK_727b872f86c7378474a8fa46147"
        FOREIGN KEY (draft_order_id) REFERENCES draft_order;


CREATE INDEX IF NOT EXISTS "IDX_e87cc617a22ef4edce5601edab" ON draft_order (display_id);


CREATE INDEX IF NOT EXISTS "IDX_5bd11d0e2a9628128e2c26fd0a" ON draft_order (cart_id);


CREATE INDEX IF NOT EXISTS "IDX_8f6dd6c49202f1466ebf21e77d" ON draft_order (order_id);


CREATE TABLE IF NOT EXISTS tracking_link
(
    id              varchar                                NOT NULL
        CONSTRAINT "PK_fcfd77feb9012ec2126d7c0bfb6" PRIMARY KEY,
    url             varchar,
    tracking_number varchar                                NOT NULL,
    fulfillment_id  varchar                                NOT NULL
        CONSTRAINT "FK_471e9e4c96e02ba209a307db32b" REFERENCES fulfillment,
    created_at      timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at      timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at      timestamp WITH TIME ZONE,
    metadata        JSONB,
    idempotency_key varchar
);


CREATE TABLE IF NOT EXISTS custom_shipping_option
(
    id                 varchar                                NOT NULL
        CONSTRAINT "PK_8dfcb5c1172c29eec4a728420cc" PRIMARY KEY,
    price              integer                                NOT NULL,
    shipping_option_id varchar                                NOT NULL
        CONSTRAINT "FK_44090cb11b06174cbcc667e91ca" REFERENCES shipping_option,
    cart_id            varchar
        CONSTRAINT "FK_93caeb1bb70d37c1d36d6701a7a" REFERENCES cart,
    created_at         timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at         timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at         timestamp WITH TIME ZONE,
    metadata           JSONB,
    CONSTRAINT "UQ_0f838b122a9a01d921aa1cdb669" UNIQUE (shipping_option_id,
                                                        cart_id)
);


CREATE INDEX IF NOT EXISTS "IDX_44090cb11b06174cbcc667e91c" ON custom_shipping_option (shipping_option_id);


CREATE INDEX IF NOT EXISTS "IDX_93caeb1bb70d37c1d36d6701a7" ON custom_shipping_option (cart_id);


CREATE TABLE IF NOT EXISTS shipping_method_tax_line
(
    id                 varchar                                NOT NULL
        CONSTRAINT "PK_54c94f5908aacbd51cf0a73edb1" PRIMARY KEY,
    rate               real                                   NOT NULL,
    name               varchar                                NOT NULL,
    code               varchar,
    created_at         timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at         timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata           JSONB,
    shipping_method_id varchar                                NOT NULL
        CONSTRAINT "FK_926ca9f29014af8091722dede08" REFERENCES shipping_method,
    CONSTRAINT "UQ_cd147fca71e50bc954139fa3104" UNIQUE (shipping_method_id,
                                                        code)
);


CREATE INDEX IF NOT EXISTS "IDX_926ca9f29014af8091722dede0" ON shipping_method_tax_line (shipping_method_id);


CREATE TABLE IF NOT EXISTS product_sales_channel
(
    product_id       varchar NOT NULL,
    sales_channel_id varchar NOT NULL
        CONSTRAINT "FK_37341bad297fe5cca91f921032b" REFERENCES sales_channel ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT "PK_fd29b6a8bd641052628dee19583" PRIMARY KEY (product_id,
                                                             sales_channel_id)
);

CREATE INDEX IF NOT EXISTS "IDX_5a4d5e1e60f97633547821ec8d" ON product_sales_channel (product_id);
CREATE INDEX IF NOT EXISTS "IDX_37341bad297fe5cca91f921032" ON product_sales_channel (sales_channel_id);


create type PAYMENT_COLLECTION_STATUS_TYPE as enum ('NOT_PAID', 'AWAITING', 'AUTHORIZED', 'PARTIALLY_AUTHORIZED', 'CANCELLED');
create type PAYMENT_COLLECTION_TYPE as enum ('ORDER_EDIT');

CREATE TABLE IF NOT EXISTS payment_collection
(
    id                varchar                                NOT NULL
        CONSTRAINT "PK_payment_collection_id" PRIMARY KEY,
    created_at        timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at        timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at        timestamp WITH TIME ZONE,
    TYPE              PAYMENT_COLLECTION_TYPE                NOT NULL,
    status            PAYMENT_COLLECTION_STATUS_TYPE         NOT NULL,
    description       text,
    amount            integer                                NOT NULL,
    authorized_amount integer,
    region_id         varchar                                NOT NULL
        CONSTRAINT "FK_payment_collection_region_id" REFERENCES region,
    currency_code     varchar                                NOT NULL,
    metadata          JSONB,
    created_by        varchar                                NOT NULL
);


CREATE TABLE IF NOT EXISTS order_edit
(
    id                    varchar                                NOT NULL
        CONSTRAINT "PK_58ab6acf2e84b4e827f5f846f7a" PRIMARY KEY,
    created_at            timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at            timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    order_id              varchar                                NOT NULL
        CONSTRAINT "FK_1f3a251488a91510f57e1bf93cd" REFERENCES "order",
    internal_note         varchar,
    created_by            varchar                                NOT NULL,
    requested_by          varchar,
    requested_at          timestamp WITH TIME ZONE,
    confirmed_by          varchar,
    confirmed_at          timestamp WITH TIME ZONE,
    declined_by           varchar,
    declined_reason       varchar,
    declined_at           timestamp WITH TIME ZONE,
    canceled_by           varchar,
    canceled_at           timestamp WITH TIME ZONE,
    payment_collection_id varchar
        CONSTRAINT "FK_order_edit_payment_collection_id" REFERENCES payment_collection
);


CREATE TABLE IF NOT EXISTS line_item
(
    id                 varchar                                NOT NULL
        CONSTRAINT "PK_cce6b13e67fa506d1d9618ac68b" PRIMARY KEY,
    cart_id            varchar
        CONSTRAINT "FK_27283ee631862266d0f1c680646" REFERENCES cart,
    order_id           varchar
        CONSTRAINT "FK_43a2b24495fe1d9fc2a9c835bc7" REFERENCES "order",
    swap_id            varchar
        CONSTRAINT "FK_3fa354d8d1233ff81097b2fcb6b" REFERENCES swap,
    title              varchar                                NOT NULL,
    description        varchar,
    thumbnail          varchar,
    is_giftcard        boolean                  DEFAULT FALSE NOT NULL,
    should_merge       boolean                  DEFAULT TRUE  NOT NULL,
    allow_discounts    boolean                  DEFAULT TRUE  NOT NULL,
    has_shipping       boolean,
    unit_price         integer                                NOT NULL,
    variant_id         varchar
        CONSTRAINT "FK_5371cbaa3be5200f373d24e3d5b" REFERENCES product_variant,
    quantity           integer                                NOT NULL
        CONSTRAINT "CHK_64eef00a5064887634f1680866" CHECK (quantity > 0),
    fulfilled_quantity integer,
    returned_quantity  integer,
    shipped_quantity   integer,
    created_at         timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at         timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata           JSONB,
    claim_order_id     varchar
        CONSTRAINT "FK_118e3c48f09a7728f41023c94ef" REFERENCES claim_order,
    is_return          boolean                  DEFAULT FALSE NOT NULL,
    original_item_id   varchar
        CONSTRAINT line_item_original_item_fk REFERENCES line_item,
    order_edit_id      varchar
        CONSTRAINT line_item_order_edit_fk REFERENCES order_edit,
    CONSTRAINT "CHK_91f40396d847f6ecfd9f752bf8" CHECK (returned_quantity <= quantity),
    CONSTRAINT "CHK_0cd85e15610d11b553d5e8fda6" CHECK (shipped_quantity <= fulfilled_quantity),
    CONSTRAINT "CHK_c61716c68f5ad5de2834c827d3" CHECK (fulfilled_quantity <= quantity)
);


CREATE TABLE IF NOT EXISTS fulfillment_item
(
    fulfillment_id varchar NOT NULL
        CONSTRAINT "FK_a033f83cc6bd7701a5687ab4b38" REFERENCES fulfillment,
    item_id        varchar NOT NULL
        CONSTRAINT "FK_e13ff60e74206b747a1896212d1" REFERENCES line_item,
    quantity       integer NOT NULL,
    CONSTRAINT "PK_bc3e8a388de75db146a249922e0" PRIMARY KEY (fulfillment_id,
                                                             item_id)
);


CREATE TABLE IF NOT EXISTS return_item
(
    return_id          varchar              NOT NULL
        CONSTRAINT "FK_7edab75b4fc88ea6d4f2574f087" REFERENCES RETURN,
    item_id            varchar              NOT NULL
        CONSTRAINT "FK_87774591f44564effd8039d7162" REFERENCES line_item,
    quantity           integer              NOT NULL,
    is_requested       boolean DEFAULT TRUE NOT NULL,
    requested_quantity integer,
    received_quantity  integer,
    metadata           JSONB,
    reason_id          varchar
        CONSTRAINT "FK_d742532378a65022e7ceb328828" REFERENCES return_reason,
    note               varchar,
    CONSTRAINT "PK_46409dc1dd5f38509b9000c3069" PRIMARY KEY (return_id,
                                                             item_id)
);

CREATE INDEX IF NOT EXISTS "IDX_27283ee631862266d0f1c68064" ON line_item (cart_id);
CREATE INDEX IF NOT EXISTS "IDX_43a2b24495fe1d9fc2a9c835bc" ON line_item (order_id);
CREATE INDEX IF NOT EXISTS "IDX_3fa354d8d1233ff81097b2fcb6" ON line_item (swap_id);
CREATE INDEX IF NOT EXISTS "IDX_5371cbaa3be5200f373d24e3d5" ON line_item (variant_id);
CREATE INDEX IF NOT EXISTS "IDX_118e3c48f09a7728f41023c94e" ON line_item (claim_order_id);
CREATE UNIQUE INDEX IF NOT EXISTS unique_li_original_item_id_order_edit_id ON line_item (order_edit_id, original_item_id)
    WHERE ((original_item_id IS NOT NULL)
        AND (order_edit_id IS NOT NULL));

create type CLAIM_ITEM_REASON_TYPE as enum ('MISSING_ITEM', 'WRONG_ITEM', 'PRODUCTION_FAILURE', 'OTHER');

CREATE TABLE IF NOT EXISTS claim_item
(
    id             varchar                                NOT NULL
        CONSTRAINT "PK_5679662039bc4c7c6bc7fa1be2d" PRIMARY KEY,
    claim_order_id varchar                                NOT NULL
        CONSTRAINT "FK_900a9c3834257304396b2b0fe7c" REFERENCES claim_order,
    item_id        varchar                                NOT NULL
        CONSTRAINT "FK_6e0cad0daef76bb642675910b9d" REFERENCES line_item,
    variant_id     varchar                                NOT NULL
        CONSTRAINT "FK_64980511ca32c8e92b417644afa" REFERENCES product_variant,
    reason         CLAIM_ITEM_REASON_TYPE                 NOT NULL,
    note           varchar,
    quantity       integer                                NOT NULL,
    created_at     timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at     timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at     timestamp WITH TIME ZONE,
    metadata       JSONB
);


CREATE TABLE IF NOT EXISTS claim_image
(
    id            varchar                                NOT NULL
        CONSTRAINT "PK_7c49e44bfe8840ca7d917890101" PRIMARY KEY,
    claim_item_id varchar                                NOT NULL
        CONSTRAINT "FK_21cbfedd83d736d86f4c6f4ce56" REFERENCES claim_item,
    url           varchar                                NOT NULL,
    created_at    timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at    timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at    timestamp WITH TIME ZONE,
    metadata      JSONB
);


CREATE INDEX IF NOT EXISTS "IDX_21cbfedd83d736d86f4c6f4ce5" ON claim_image (claim_item_id);


CREATE INDEX IF NOT EXISTS "IDX_900a9c3834257304396b2b0fe7" ON claim_item (claim_order_id);


CREATE INDEX IF NOT EXISTS "IDX_6e0cad0daef76bb642675910b9" ON claim_item (item_id);


CREATE INDEX IF NOT EXISTS "IDX_64980511ca32c8e92b417644af" ON claim_item (variant_id);


CREATE TABLE IF NOT EXISTS claim_item_tags
(
    item_id varchar NOT NULL
        CONSTRAINT "FK_c2c0f3edf39515bd15432afe6e5" REFERENCES claim_item ON DELETE CASCADE,
    tag_id  varchar NOT NULL
        CONSTRAINT "FK_dc9bbf9fcb9ba458d25d512811b" REFERENCES claim_tag ON DELETE CASCADE,
    CONSTRAINT "PK_54ab8ce0f7e99167068188fbd81" PRIMARY KEY (item_id,
                                                             tag_id)
);


CREATE INDEX IF NOT EXISTS "IDX_c2c0f3edf39515bd15432afe6e" ON claim_item_tags (item_id);


CREATE INDEX IF NOT EXISTS "IDX_dc9bbf9fcb9ba458d25d512811" ON claim_item_tags (tag_id);


CREATE TABLE IF NOT EXISTS line_item_tax_line
(
    id         varchar                                NOT NULL
        CONSTRAINT "PK_4a0f4322fcd5ce4af85727f89a8" PRIMARY KEY,
    rate       real                                   NOT NULL,
    name       varchar                                NOT NULL,
    code       varchar,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    metadata   JSONB,
    item_id    varchar                                NOT NULL
        CONSTRAINT "FK_5077fa54b0d037e984385dfe8ad" REFERENCES line_item ON DELETE CASCADE,
    CONSTRAINT "UQ_3c2af51043ed7243e7d9775a2ad" UNIQUE (item_id,
                                                        code)
);


CREATE INDEX IF NOT EXISTS "IDX_5077fa54b0d037e984385dfe8a" ON line_item_tax_line (item_id);


CREATE TABLE IF NOT EXISTS line_item_adjustment
(
    id          varchar NOT NULL
        CONSTRAINT "PK_2b1360103753df2dc8257c2c8c3" PRIMARY KEY,
    item_id     varchar NOT NULL
        CONSTRAINT "FK_be9aea2ccf3567007b6227da4d2" REFERENCES line_item ON DELETE CASCADE,
    description varchar NOT NULL,
    discount_id varchar
        CONSTRAINT "FK_2f41b20a71f30e60471d7e3769c" REFERENCES discount,
    amount      numeric NOT NULL,
    metadata    JSONB
);


CREATE INDEX IF NOT EXISTS "IDX_be9aea2ccf3567007b6227da4d" ON line_item_adjustment (item_id);


CREATE INDEX IF NOT EXISTS "IDX_2f41b20a71f30e60471d7e3769" ON line_item_adjustment (discount_id);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_bf701b88d2041392a288785ada" ON line_item_adjustment (discount_id, item_id)
    WHERE (discount_id IS NOT NULL);


create type ORDER_ITEM_CHANGE_TYPE as enum ('ITEM_ADD', 'ITEM_REMOVE', 'ITEM_UPDATE');
CREATE TABLE IF NOT EXISTS order_item_change
(
    id                    varchar                                NOT NULL
        CONSTRAINT "PK_d6eb138f77ffdee83567b85af0c" PRIMARY KEY,
    created_at            timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at            timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at            timestamp WITH TIME ZONE,
    TYPE                  ORDER_ITEM_CHANGE_TYPE                 NOT NULL,
    order_edit_id         varchar                                NOT NULL
        CONSTRAINT "FK_44feeebb258bf4cfa4cc4202281" REFERENCES order_edit,
    original_line_item_id varchar
        CONSTRAINT "FK_b4d53b8d03c9f5e7d4317e818d9" REFERENCES line_item,
    line_item_id          varchar
        CONSTRAINT "REL_5f9688929761f7df108b630e64" UNIQUE
        CONSTRAINT "FK_5f9688929761f7df108b630e64a" REFERENCES line_item,
    CONSTRAINT "UQ_da93cee3ca0dd50a5246268c2e9" UNIQUE (order_edit_id,
                                                        line_item_id),
    CONSTRAINT "UQ_5b7a99181e4db2ea821be0b6196" UNIQUE (order_edit_id,
                                                        original_line_item_id)
);

CREATE INDEX IF NOT EXISTS "IDX_order_edit_payment_collection_id" ON order_edit (payment_collection_id);
CREATE INDEX IF NOT EXISTS "IDX_order_edit_order_id" ON order_edit (order_id);
CREATE INDEX IF NOT EXISTS "IDX_payment_collection_region_id" ON payment_collection (region_id)
    WHERE (deleted_at IS NULL);
CREATE INDEX IF NOT EXISTS "IDX_payment_collection_currency_code" ON payment_collection (currency_code)
    WHERE (deleted_at IS NULL);


CREATE TABLE IF NOT EXISTS payment_collection_sessions
(
    payment_collection_id varchar NOT NULL
        CONSTRAINT "FK_payment_collection_sessions_payment_collection_id" REFERENCES payment_collection ON DELETE CASCADE,
    payment_session_id    varchar NOT NULL
        CONSTRAINT "FK_payment_collection_sessions_payment_session_id" REFERENCES payment_session ON DELETE CASCADE,
    CONSTRAINT "PK_payment_collection_sessions" PRIMARY KEY (payment_collection_id,
                                                             payment_session_id)
);

CREATE INDEX IF NOT EXISTS "IDX_payment_collection_sessions_payment_collection_id" ON payment_collection_sessions (payment_collection_id);
CREATE INDEX IF NOT EXISTS "IDX_payment_collection_sessions_payment_session_id" ON payment_collection_sessions (payment_session_id);

CREATE TABLE IF NOT EXISTS payment_collection_payments
(
    payment_collection_id varchar NOT NULL
        CONSTRAINT "FK_payment_collection_payments_payment_collection_id" REFERENCES payment_collection ON DELETE CASCADE,
    payment_id            varchar NOT NULL
        CONSTRAINT "FK_payment_collection_payments_payment_id" REFERENCES payment ON DELETE CASCADE,
    CONSTRAINT "PK_payment_collection_payments" PRIMARY KEY (payment_collection_id,
                                                             payment_id)
);

CREATE INDEX IF NOT EXISTS "IDX_payment_collection_payments_payment_collection_id" ON payment_collection_payments (payment_collection_id);
CREATE INDEX IF NOT EXISTS "IDX_payment_collection_payments_payment_id" ON payment_collection_payments (payment_id);


CREATE TABLE IF NOT EXISTS analytics_config
(
    id         varchar                                NOT NULL
        CONSTRAINT "PK_93505647c5d7cb479becb810b0f" PRIMARY KEY,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    deleted_at timestamp WITH TIME ZONE,
    user_id    varchar                                NOT NULL,
    opt_out    boolean                  DEFAULT FALSE NOT NULL,
    anonymize  boolean                  DEFAULT FALSE NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS "IDX_379ca70338ce9991f3affdeedf" ON analytics_config (id, user_id)
    WHERE (deleted_at IS NULL);


CREATE TABLE IF NOT EXISTS publishable_api_key_sales_channel
(
    sales_channel_id   varchar NOT NULL,
    publishable_key_id varchar NOT NULL,
    CONSTRAINT "PK_68eaeb14bdac8954460054c567c" PRIMARY KEY (sales_channel_id,
                                                             publishable_key_id)
);


CREATE TABLE IF NOT EXISTS publishable_api_key
(
    id         varchar                                NOT NULL
        CONSTRAINT "PK_9e613278673a87de92c606b4494" PRIMARY KEY,
    created_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    created_by varchar,
    revoked_by varchar,
    revoked_at timestamp WITH TIME ZONE,
    title      varchar                                NOT NULL
);


CREATE TABLE IF NOT EXISTS sales_channel_location
(
    id               varchar                                NOT NULL
        CONSTRAINT "PK_afd2c2c52634bc8280a9c9ee533" PRIMARY KEY,
    created_at       timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at       timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    sales_channel_id text                                   NOT NULL,
    location_id      text                                   NOT NULL,
    deleted_at       timestamp WITH TIME ZONE
);


CREATE INDEX IF NOT EXISTS "IDX_sales_channel_location_sales_channel_id" ON sales_channel_location (sales_channel_id)
    WHERE (deleted_at IS NULL);


CREATE INDEX IF NOT EXISTS "IDX_sales_channel_location_location_id" ON sales_channel_location (location_id)
    WHERE (deleted_at IS NULL);


CREATE TABLE IF NOT EXISTS product_variant_inventory_item
(
    id                varchar                                NOT NULL
        CONSTRAINT "PK_9a1188b8d36f4d198303b4f7efa" PRIMARY KEY,
    created_at        timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at        timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    inventory_item_id text                                   NOT NULL,
    variant_id        text                                   NOT NULL,
    required_quantity integer                  DEFAULT 1     NOT NULL,
    deleted_at        timestamp WITH TIME ZONE,
    CONSTRAINT "UQ_c9be7c1b11a1a729eb51d1b6bca" UNIQUE (variant_id,
                                                        inventory_item_id)
);


CREATE INDEX IF NOT EXISTS "IDX_product_variant_inventory_item_inventory_item_id" ON product_variant_inventory_item (inventory_item_id)
    WHERE (deleted_at IS NULL);


CREATE INDEX IF NOT EXISTS "IDX_product_variant_inventory_item_variant_id" ON product_variant_inventory_item (variant_id)
    WHERE (deleted_at IS NULL);


CREATE TABLE IF NOT EXISTS product_category
(
    id                 varchar                                   NOT NULL
        CONSTRAINT "PK_qgguwbn1cwstxk93efl0px9oqwt" PRIMARY KEY,
    name               text                                      NOT NULL,
    handle             text                                      NOT NULL,
    parent_category_id varchar,
    mpath              text,
    is_active          boolean                  DEFAULT FALSE,
    is_internal        boolean                  DEFAULT FALSE,
    created_at         timestamp WITH TIME ZONE DEFAULT now()    NOT NULL,
    updated_at         timestamp WITH TIME ZONE DEFAULT now()    NOT NULL,
    rank               integer                  DEFAULT 0        NOT NULL
        CONSTRAINT product_category_rank_check CHECK (rank >= 0),
    description        text                     DEFAULT ''::text NOT NULL
);


CREATE INDEX IF NOT EXISTS "IDX_product_category_path" ON product_category (mpath);


CREATE UNIQUE INDEX IF NOT EXISTS "UniqProductCategoryParentIdRank" ON product_category (parent_category_id, rank);


CREATE UNIQUE INDEX IF NOT EXISTS "IDX_product_category_handle" ON product_category (handle);


CREATE INDEX IF NOT EXISTS "IDX_product_category_active_public" ON product_category (parent_category_id, is_active, is_internal)
    WHERE ((is_active IS TRUE)
        AND (is_internal IS FALSE));


CREATE TABLE IF NOT EXISTS product_category_product
(
    product_category_id varchar NOT NULL
        CONSTRAINT "FK_product_category_id" REFERENCES product_category ON DELETE CASCADE,
    product_id          varchar NOT NULL
        CONSTRAINT "FK_product_id" REFERENCES product ON DELETE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS "IDX_upcp_product_id_product_category_id" ON product_category_product (product_category_id, product_id);
CREATE INDEX IF NOT EXISTS "IDX_pcp_product_category_id" ON product_category_product (product_category_id);
CREATE INDEX IF NOT EXISTS "IDX_pcp_product_id" ON product_category_product (product_id);


CREATE TABLE IF NOT EXISTS product_shipping_profile
(
    profile_id text NOT NULL,
    product_id text NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_product_shipping_profile_profile_id_product_id_unique ON product_shipping_profile (profile_id, product_id);
CREATE INDEX IF NOT EXISTS idx_product_shipping_profile_product_id ON product_shipping_profile (product_id);
CREATE INDEX IF NOT EXISTS idx_product_shipping_profile_profile_id ON product_shipping_profile (profile_id);


CREATE TABLE IF NOT EXISTS product_variant_money_amount
(
    id              VARCHAR                                NOT NULL
        CONSTRAINT "PK_product_variant_money_amount" PRIMARY KEY,
    money_amount_id text                                   NOT NULL,
    variant_id      text                                   NOT NULL,
    deleted_at      timestamp WITH TIME ZONE,
    created_at      timestamp WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at      timestamp WITH TIME ZONE DEFAULT now() NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_product_variant_money_amount_money_amount_id_unique ON product_variant_money_amount (money_amount_id);
CREATE INDEX IF NOT EXISTS idx_product_variant_money_amount_variant_id ON product_variant_money_amount (variant_id);
