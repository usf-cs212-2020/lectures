# normal
SELECT
CONCAT(first, ' ', IFNULL(CONCAT(middle, ' '), ''), last) AS 'name',
CONCAT(usfid, '@usfca.edu') AS 'email',
IFNULL(twitterid, '') AS 'twitter',
GROUP_CONCAT(course ORDER BY course SEPARATOR ', ') AS 'courses'
FROM faculty_names
NATURAL LEFT OUTER JOIN faculty_twitter
NATURAL LEFT OUTER JOIN faculty_courses
GROUP BY faculty_names.usfid
ORDER BY last ASC;

# order by courses
SELECT
CONCAT(first, ' ', IFNULL(CONCAT(middle, ' '), ''), last) AS 'name',
CONCAT(usfid, '@usfca.edu') AS 'email',
IFNULL(twitterid, '') AS 'twitter',
GROUP_CONCAT(course ORDER BY course SEPARATOR ', ') AS 'courses'
FROM faculty_names
NATURAL LEFT OUTER JOIN faculty_twitter
NATURAL LEFT OUTER JOIN faculty_courses
GROUP BY faculty_names.usfid
ORDER BY courses ASC;

# only twitter accounts
SELECT
CONCAT(first, ' ', IFNULL(CONCAT(middle, ' '), ''), last) AS 'name',
CONCAT(usfid, '@usfca.edu') AS 'email',
IFNULL(twitterid, '') AS 'twitter',
GROUP_CONCAT(course ORDER BY course SEPARATOR ', ') AS 'courses'
FROM faculty_names
NATURAL LEFT OUTER JOIN faculty_twitter
NATURAL LEFT OUTER JOIN faculty_courses
WHERE twitterid IS NOT NULL
GROUP BY faculty_names.usfid
ORDER BY courses ASC;

# only david first name
SELECT
CONCAT(first, ' ', IFNULL(CONCAT(middle, ' '), ''), last) AS 'name',
CONCAT(usfid, '@usfca.edu') AS 'email',
IFNULL(twitterid, '') AS 'twitter',
GROUP_CONCAT(course ORDER BY course SEPARATOR ', ') AS 'courses'
FROM faculty_names
NATURAL LEFT OUTER JOIN faculty_twitter
NATURAL LEFT OUTER JOIN faculty_courses
WHERE first LIKE 'David'
GROUP BY faculty_names.usfid
ORDER BY courses ASC;

# only 200-level courses
SELECT
CONCAT(first, ' ', IFNULL(CONCAT(middle, ' '), ''), last) AS 'name',
CONCAT(usfid, '@usfca.edu') AS 'email',
IFNULL(twitterid, '') AS 'twitter',
GROUP_CONCAT(course ORDER BY course SEPARATOR ', ') AS 'courses'
FROM faculty_names
NATURAL LEFT OUTER JOIN faculty_twitter
NATURAL LEFT OUTER JOIN faculty_courses
GROUP BY faculty_names.usfid
HAVING courses LIKE 'CS 2%%'
ORDER BY last ASC;
