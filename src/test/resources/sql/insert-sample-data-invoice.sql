
insert into invoice_type (id, code, name, status_record, created, created_by, updated, updated_by)
values ('registrasi', 'REG-001', 'Biaya Pendaftaran', 'ACTIVE', current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM');

insert into invoice_type (id, code, name, status_record, created, created_by, updated, updated_by)
values ('donasi', 'DONASI-001', 'Sumbangan sukarela', 'ACTIVE', current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM');

insert into invoice_type (id, code, name, status_record, created, created_by, updated, updated_by)
values ('uang muka', 'DP-001', 'Uang Muka', 'ACTIVE', current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM');

--customer
insert into customer(id, code, name, email, mobile_phone, status_record, created_by, updated, updated_by)
values ('c001', 'CUST-001', 'Customer 001', 'c001@yomail.com', '081234567890', 'ACTIVE', '2022-07-21T10:17:00.219261900', 'Test User', '2022-07-21T10:17:00.219261900', 'Test User')