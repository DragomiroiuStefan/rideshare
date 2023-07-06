--------Get Rides-----------------------

SELECT r.*,
       rc_dep.departure_time,
       rc_arr.arrival_time
FROM rides r
         JOIN ride_connections rc_dep ON r.ride_id = rc_dep.ride_id
         JOIN ride_connections rc_arr ON r.ride_id = rc_arr.ride_id
WHERE rc_dep.departure_location = 5
  AND rc_arr.arrival_location = 7
  AND rc_dep.departure_time < rc_arr.arrival_time
  AND DATE(rc_dep.departure_time) = '2023-06-27';

select rc.connection_id, rc.price, 4 - count(bc.booking_id) as available_seats
from ride_connections rc
         left join booking_connections bc on rc.connection_id = bc.connection_id
where rc.ride_id = 4
  and rc.departure_time >= '2023-06-27 19:36:43.000000'
  and rc.arrival_time <= '2023-06-27 21:36:43.000000'
group by rc.connection_id;

SELECT COUNT(rr.rating), AVG(rr.rating) AS average_rating
FROM users u
         JOIN rides r ON u.user_id = r.driver
         JOIN ride_ratings rr ON r.ride_id = rr.ride_id
WHERE u.user_id = 1;

--------Get Ride-------------------------------


