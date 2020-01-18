DROP TABLE IF EXISTS virtual_wallet;

CREATE TABLE virtual_wallet (
    id BIGINT NOT NULL auto_increment primary key COMMENT '虚拟钱包id',
    create_time BIGINT NOT NULL COMMENT '创建时间',
    balance DECIMAL NOT NULL DEFAULT 0 COMMENT '余额'
);

DROP TABLE IF EXISTS virtual_wallet_transaction;

CREATE TABLE virtual_wallet_transaction (
    id BIGINT NOT NULL auto_increment primary key COMMENT '钱包交易流水id',
    create_time BIGINT NOT NULL COMMENT '交易时间',
    amount DECIMAL NOT NULL COMMENT '交易金额',
    type tinyint not null comment '交易类型 1 充值；2 提现；3 支付',
    from_wallet_id BIGINT NOT NULL COMMENT '出账钱包账号',
    to_wallet_id BIGINT NOT NULL COMMENT '入账钱包账号',
    status tinyint not null comment '交易状态 1 待执行；2 已执行；3 失败；4 关闭'
);