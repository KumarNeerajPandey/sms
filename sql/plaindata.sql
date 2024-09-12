INSERT INTO student (
    first_name, middle_name, last_name, dob, gender,
    phn_num, email_id, father_name, father_phn_num,
    identity_type, identity_number, nationality
) VALUES (
    'Neeraj', 'Kumar', 'Pandey', '1990-09-3', 'MALE',
    '7416621236', 'neeraj@gmail.com', 'MP Pandey', '9039556461',
    'PAN', 'CXZPP7689L', 'India'
);;


INSERT INTO address (
    address_line1, address_line2, city, state, country, postal_code, student_id
) VALUES (
    '123', 'Near Durga Temple', 'Satna', 'MP', 'India', '485778', 1
);

INSERT INTO address (
    address_line1, address_line2, city, state, country, postal_code, student_id
) VALUES (
    '121', 'Village Ghuisa Block Amarpatan', 'Satna', 'MP', 'India', '485775', 1
);
