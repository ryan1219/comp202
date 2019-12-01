def which_delimiter(input):
    space = input.count(" ")
    tab = input.count('\t')
    comma = input.count(',')

    if space == tab == comma == 0:
        raise AssertionError

    if (space >= tab) and (space >= comma):
        return ' '
    elif (tab >= space) and (tab >= comma):
        return '\t'
    else:
        return ','


# which_delimiter("abc")

def stage_one(input_filename, output_filename):
    # out file
    out_file = open(output_filename, 'w', encoding='utf-8')
    line_written = 0
    # Open file
    with open(input_filename, "r", encoding='utf-8') as fileHandler:
        # Read each line in loop
        for line in fileHandler:
            # line.replace(which_delimiter(line), "\t")
            # As each line (except last one) will contain new line character, so strip that
            line = line.strip()
            line_list = line.split(which_delimiter(line))
            # change / or . to -
            line_list[2] = line_list[2].replace('.', '-')
            line_list[2] = line_list[2].replace('/', '-')
            line_list[3] = line_list[3].replace('/', '-')
            line_list[3] = line_list[3].replace('.', '-')
            # change all text to uppercase
            upper = [x.upper() for x in line_list]
            # write to output with tab delimited
            out_file.write("\t".join(upper) + '\n')
            line_written += 1
    out_file.close()
    return line_written

stage_one("./10.txt", "./10.1.out.txt")
def stage_two(input_filename, output_filename):
    # out file
    out_file = open(output_filename, 'w', encoding='utf-8')
    line_written = 0
    # Open file
    with open(input_filename, "r", encoding='utf-8') as fileHandler:
        for line in fileHandler:
            # As each line (except last one) will contain new line character, so strip that
            line = line.strip()
            line_list = line.split(which_delimiter(line))
            if len(line_list) == 9:
                out_file.write(line + '\n')
            else:
                temperature_list = line_list[7:len(line_list)-1]
                temperature = temperature_list[0]
                i = 1
                while i < len(temperature_list):
                    if has_numbers(temperature_list[i]):
                        temperature = temperature + '.' + temperature_list[i].replace(' ', '')
                    else:
                        temperature = temperature + temperature_list[i].replace(' ', '')
                    i += 1
                out_file.write("\t".join(line_list[:7]) + '\t' + temperature + '\t' + line_list[len(line_list) - 1] + '\n')
            line_written += 1

    out_file.close()
    return line_written


def has_numbers(input_string):
    return any(char.isdigit() for char in input_string)


stage_two("10.1.out.txt", "10.2.out.txt")