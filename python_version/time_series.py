from datetime import datetime
import matplotlib.pyplot as plt


def date_diff(d1, d2):
    d1 = datetime.strptime(d1, "%Y-%m-%d")
    d2 = datetime.strptime(d2, "%Y-%m-%d")
    return (d2 - d1).days


def get_age(d1, d2):
    one_year = 365.2425
    return int(date_diff(d2, d1) // one_year)


def stage_three(input_filename, output_filename):
    # out file
    out_file = open(output_filename, 'w', encoding='utf-8')
    in_file = open(input_filename, 'r', encoding='utf-8')
    first_line = in_file.readline()
    in_file.close()
    index_date = first_line.split("\t")[2]
    result = {}
    # Open file
    with open(input_filename, "r", encoding='utf-8') as fileHandler:
        # Read each line in loop
        for line in fileHandler:
            # As each line (except last one) will contain new line character, so strip that
            line = line.strip()
            line_list = line.split("\t")
            # record days diff
            line_list[2] = str(date_diff(index_date, line_list[2]))
            if line_list[2] not in result:
                result[line_list[2]] = {'I': 0, 'D': 0, 'R': 0}
            # age
            line_list[3] = str(abs(get_age(index_date, line_list[3])))
            # status
            if line_list[6].startswith('I'):
                line_list[6] = 'I'
                result[line_list[2]]['I'] = result[line_list[2]]['I'] + 1
            elif line_list[6].startswith('R'):
                line_list[6] = 'R'
                result[line_list[2]]['R'] = result[line_list[2]]['R'] + 1
            elif line_list[6].startswith('D') or line_list[6].startswith('M'):
                line_list[6] = 'D'
                result[line_list[2]]['D'] = result[line_list[2]]['D'] + 1

            # write to output with tab delimited
            out_file.write("\t".join(line_list) + '\n')

    out_file.close()
    return result


def plot_time_series(d):
    result = []
    days = []
    I = []
    R = []
    D = []
    for key, value in sorted(d.items()):
        days.append(key)
        I.append(value['I'])
        R.append(value['R'])
        D.append(value['D'])
        result.append([value['I'], value['R'], value['D']])

    plt.plot(days, I)
    plt.plot(days, R)
    plt.plot(days, D)

    plt.legend(['Infected', 'Recovered', 'Dead'], loc='upper left')
    plt.xlabel('Days into Pandemic')
    plt.ylabel('Number of People')
    plt.title('Time series of early pandemic, by')
    plt.savefig('time_series.png')
    return result

d = stage_three("10.2.out.txt", "10.3.out.txt")
plot_time_series(d)
# print(date_diff('2019-10-31', '2019-10-2'))