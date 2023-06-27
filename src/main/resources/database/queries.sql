
select ride_id
from ride_connections
where departure_location = 2
and date(departure_time) = '2023-06-19';
-- => ride_id = 1, departure_time = x


select *
from ride_connections
where ride_id = 1
order by departure_time;

