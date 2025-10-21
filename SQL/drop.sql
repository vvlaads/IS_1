DROP TABLE IF EXISTS MOVIES CASCADE;
DROP TABLE IF EXISTS PERSONS CASCADE;
DROP TABLE IF EXISTS COORDINATES CASCADE;
DROP TABLE IF EXISTS LOCATIONS CASCADE;

DROP FUNCTION delete_movie_by_golden_palm_count(INTEGER);
DROP FUNCTION get_movies_by_name_prefix(varchar);
DROP FUNCTION get_movies_by_golden_palm_count(INTEGER);
DROP FUNCTION get_operators_without_oscars();
DROP FUNCTION reward_long_movies(min_length INT, oscars_to_add INT);
