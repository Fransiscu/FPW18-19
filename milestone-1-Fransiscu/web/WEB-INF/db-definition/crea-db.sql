create table user (
	id_user serial primary key,
	name varchar(24),
	surname varchar(24),
	email varchar(50),
	password varchar(24),
	birthDate date,
	entity varchar(24),
	organizer tinyint,
	author tinyint
);

create table paper (
	id_paper serial primary key,
	id_user bigint unsigned,
	title varchar(50),
	submissionDate date,
	content varchar(300),
	picture varchar(50),
	category varchar(15),
	status varchar(15),
	foreign key (id_user) references user(id_user) on update cascade on delete cascade
);

create table grade (
	id_paper bigint unsigned,
	id_user bigint unsigned,
	isGraded tinyint,
	grade tinyint,
	gradingDate date,
	authorComments varchar(100),
	organizerComments varchar(100),
	primary key (id_paper, id_user),	
	foreign key (id_paper) references paper(id_paper) on update cascade on delete cascade,
	foreign key (id_user) references user(id_user) on update cascade on delete cascade
);

create table reviewer (
	id_review serial primary key,
	id_paper bigint unsigned,
	id_user bigint unsigned,
	foreign key (id_paper) references paper(id_paper) on update cascade on delete cascade,
	foreign key (id_user) references user(id_user) on update cascade on delete cascade 
);
