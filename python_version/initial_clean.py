def which_delimiter(input):
    space = input.count(" ")
    tab = input.count('\t')
    comma = input.count(',')
    if (space >= tab) and (space >= comma):
        return ' '
    elif (tab >= space) and (tab >= comma):
        return '\t'
    else:
        return ','


def stage_one(input_filename, output_filename):
    # out file
    out_file = open(output_filename, 'w', encoding='utf - 8')
    line_written = 0
    # Open file
    with open(input_filename, "r", encoding='utf-8') as fileHandler:
        # Read each line in loop
        for line in fileHandler:
            # As each line (except last one) will contain new line character, so strip that
            line = line.strip()
            line_list = line.split(which_delimiter(line))
            # change / or . to -
            line_list[2].replace('/', '-')
            line_list[2].replace('.', '-')
            # change all text to uppercase
            upper = [x.lower() for x in line_list]
            # write to output with tab delimited
            out_file.write("\t".join(upper) + '\n')
            line_written += 1
    out_file.close()
    return line_written


def stage_two(input_filename, output_filename):
    # out file
    out_file = open(output_filename, 'w', encoding='utf - 8')
    line_written = 0
    # Open file
    with open(input_filename, "r", encoding='utf-8') as fileHandler:
        for line in fileHandler:
            # As each line (except last one) will contain new line character, so strip that
            line = line.strip()
            line_list = line.split(which_delimiter(line))
            if len(line_list) == 9:
                out_file.write(line)
            else:
                temperature_list = line_list[7:len[line_list]]
                temperature = temperature_list[0]
                i = 1
                while i < len(temperature_list):
                    if has_numbers(temperature_list[i]):
                        temperature = temperature + '.' + temperature_list[i].replace(' ', '')
                out_file.write("\t".join(line_list[:7]) + '\t' + temperature + '\t' + line_list(len(line_list)))
            line_written += 1

    out_file.close()
    return line_written


def has_numbers(inputString):
    return any(char.isdigit() for char in inputString)

