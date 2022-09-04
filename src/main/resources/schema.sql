CREATE TABLE message (
	id VARCHAR(36) default random_uuid(),
	content CHARACTER LARGE OBJECT not null,
	primary key (id)
);
CREATE TABLE scheduled_message (
	id VARCHAR(36) default random_uuid(),
	message_id VARCHAR(36) not null,
	appointment_date_time DATETIME not null,
	_to VARCHAR(255) not null, 
	status VARCHAR(255) not null,
	primary key (id)
);