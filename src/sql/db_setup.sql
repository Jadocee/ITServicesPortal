-- counters
create view performance_view as
select [category],
       sum(case
               when (state = 'NEW' or state = 'PROGRESS' or state = 'COMPLETE') THEN 1
               ELSE 0 end)                                                                            as 'unresolved',
       sum(case when state = 'RESOLVED' and date >= dateadd(day, -7, getutcdate()) THEN 1 ELSE 0 end) as 'resolved'
from Issue
group by [category]
go

create view unresolved_count as
select name,
       sum(case
               when (state = 'NEW' or state = 'PROGRESS' or state = 'COMPLETE') THEN 1
               ELSE 0 end) as 'unresolved'
from Issue,
     Category
group by [name]

go


select *
from unresolved_count
order by case
             when [category] = 'NETWORK' then '1'
             when [category] = 'SOFTWARE' then '2'
             when [category] = 'HARDWARE' then '3'
             when [category] = 'ACCOUNT' then '4'
             when [category] = 'EMAIL' then '5'
             else [category]
             end
go



begin transaction
declare @unresolvedCount float;
declare @itstaffCount float;
select @itstaffCount = cast((sum(case when role = 'ITSTAFF' then 1 else 0 end) * 5) as float)
from Client;
select @unresolvedCount =
       cast(sum(case when (state = 'NEW' or state = 'PROGRESS' or state = 'COMPLETE') then 1 else 0 end) as float)
from Issue;
select @unresolvedCount / @itstaffCount as [stress rate];
commit


begin transaction
declare @unresolvedCount float;
declare @itstaffCount float;
select @itstaffCount = cast((sum(case when role = 'ITSTAFF' then 1 else 0 end) * 5) as float)
from Client;
select @itstaffCount as 'count';
commit