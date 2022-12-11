create table main.Client
(
    id                      integer           not null
        constraint Client_pk
            primary key autoincrement,
    email                   text              not null,
    password                text              not null,
    firstName               text              not null,
    lastName                text              not null,
    contactNum              text              not null,
    role                    text              not null,
    isAccountNonExpired     integer default 1 not null,
    isAccountNonLocked      integer default 1 not null,
    isCredentialsNonExpired integer default 1 not null,
    isEnabled               integer default 1 not null
);

create table main.Comment
(
    id          integer
        constraint Comment_pk
            primary key,
    date        datetime2 default getutcdate() not null,
    message     nvarchar(600)                  not null,
    recommended bit       default 0            not null
)
go

create table ClientComment
(
    client_id  int not null
        constraint ClientComment_Client_id_fk
            references Client
            on update cascade on delete cascade,
    comment_id int not null
        constraint ClientComment_Comment_id_fk
            references Comment
            on update cascade on delete cascade,
    constraint ClientComment_pk
        primary key (client_id, comment_id)
)
go

create unique index Comment_id_uindex
    on Comment (id)
go

create table Issue
(
    id            int identity
        constraint Issue_pk
            primary key,
    title         varchar(100)                  not null,
    [desc]        nvarchar(1000)                not null,
    date          datetime2   default getdate() not null,
    tags          varchar(30) default NULL,
    state         varchar(8)  default 'NEW'     not null,
    category      varchar(8)                    not null,
    subcategory   varchar(11)                   not null,
    resolved_date datetime2   default NULL
)
go

create table ClientIssue
(
    client_id int not null
        constraint ClientIssue_Client_id_fk
            references Client
            on update cascade on delete cascade,
    issue_id  int not null
        constraint ClientIssue_Issue_id_fk
            references Issue
            on update cascade on delete cascade,
    constraint ClientIssue_pk
        primary key (client_id, issue_id)
)
go

create unique index Issue_id_uindex
    on Issue (id)
go

create table IssueComment
(
    issue_id   int not null
        constraint IssueComment_Issue_id_fk
            references Issue
            on update cascade on delete cascade,
    comment_id int not null
        constraint IssueComment_Comment_id_fk
            references Comment
            on update cascade on delete cascade,
    constraint IssueComment_pk
        primary key (issue_id, comment_id)
)
go

create table KnowledgeBase
(
    id         int identity
        constraint KnowledgeBase_pk
            primary key,
    date_added datetime2 default getutcdate() not null,
    issue_id   int                            not null
        constraint KnowledgeBase_Issue_id_fk
            references Issue
            on update cascade on delete cascade
)
go

create unique index KnowledgeBase_id_uindex
    on KnowledgeBase (id)
go

create view resolved_last_7_days as
select dateadd(day, 0, datediff(day, 0, date))                                                        as 'date',
       sum(case when state = 'RESOLVED' and date >= dateadd(day, -7, getutcdate()) then 1 else 0 end) as 'nResolved'
from Issue
where date >= dateadd(day, -7, getutcdate())
group by dateadd(day, 0, datediff(day, 0, date))
go

create view unresolved_count as
select [category],
       sum(case
               when (state = 'NEW' or state = 'PROGRESS' or state = 'COMPLETE') THEN 1
               ELSE 0 end) as 'unresolved'
from Issue
group by [category]
go

