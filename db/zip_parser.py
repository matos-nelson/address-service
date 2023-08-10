import csv

import os

cur_path = os.path.dirname(__file__)
state_set = set()
zip_set = set()

def processContents(states, zips):
    state_set = sorted(states)
    state_file_path = os.path.relpath('.\\sql\\states.sql', cur_path)
    state_file = open(state_file_path, "w")
    for value in state_set:
        v = value.split(",")
        insert_state_sql = "INSERT INTO state(code, name) values ('" + v[0] + "', '" + v[1] + "');\n"
        state_file.write(insert_state_sql)
    state_file.close()

    zip_set = sorted(zips)
    zip_file_path = os.path.relpath('.\\sql\\zips.sql', cur_path)
    zip_file = open(zip_file_path, "w")
    for zip_info in zip_set:
        z = zip_info.split(",")
        insert_zip_sql = "INSERT INTO zip(code, city, state_id) values ('" + z[0] + "', " + "\"" + z[1] + "\" , (select id from state where code = '" + z[2] + "' and name = '" + z[3] + "'));\n"
        zip_file.write(insert_zip_sql)
    zip_file.close()

#main

uszipsFile = 'uszips.csv'
with open(uszipsFile) as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
        if line_count == 0:
            print(f'Column names are {", ".join(row)}')
            line_count += 1
        else:
            zip_set.add(row[0] + "," + row[3] + "," + row[4] + "," + row[5])
            state_set.add(row[4] + "," + row[5])
            line_count += 1
    print(f'Processed {line_count} lines ' + uszipsFile + '.')


armedForcesZips = 'armed-forces-zips.csv'
with open(armedForcesZips) as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
        if line_count == 0:
            print(f'Column names are {", ".join(row)}')
            line_count += 1
        else:
            zip_set.add(row[0] + "," + row[1] + "," + row[2] + "," + row[3])
            state_set.add(row[2] + "," + row[3])
            line_count += 1
    print(f'Processed {line_count} lines ' + armedForcesZips + '.')


processContents(state_set, zip_set)