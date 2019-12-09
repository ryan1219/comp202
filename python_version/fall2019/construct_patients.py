import matplotlib.pyplot as plt


class Patient:
    def __init__(self, num, day_diagnosed, age, sex_gender, postal, state, temps, days_symptomatic):
        self.num = int(num)
        self.day_diagnosed = int(day_diagnosed)
        self.age = int(age)
        if sex_gender.startswith('M') or sex_gender.startswith('H') or sex_gender == 'BOY':
            self.sex_gender = 'M'
        elif sex_gender.startswith('F') or sex_gender.startswith('W') or sex_gender == 'GIRL':
            self.sex_gender = 'F'
        else:
            self.sex_gender = 'X'
        self.postal = self.validate_postal(postal)
        self.state = state
        self.temps = [self.parse_temps(temps)]
        self.days_symptomatic = int(days_symptomatic)

    def parse_temps(self, temps):
        if 'N' in temps and 'A' in temps:
            return 0.0

        temps = temps.replace(',', '.')
        temps = temps.replace('°', '')
        temps = temps.replace(' ', '')
        new = ''
        for letter in temps:
            if not (letter.isalpha()):
                new += letter
        if new == '':
            return 0.0

        temps = float(new)
        if temps > 45:
            return (temps - 32) * 5 / 9
        else:
            return temps

    def validate_postal(self, postal):
        if len(postal) < 3 or postal == 'N.A.':
            return '000'
        if postal[0] == 'H' and postal[1].isdigit() and postal[2].isalpha():
            return postal[:3]
        return '000'

    def __str__(self):
        return str(self.num) + '\t' + str(self.age) + '\t' + self.sex_gender + '\t' + self.postal + '\t' \
               + str(self.day_diagnosed) + '\t' + self.state + '\t' + str(self.days_symptomatic)\
               + '\t' + ';'.join([str(x) for x in self.temps])

    def update(self, patient):
        if patient.num == self.num and patient.sex_gender == self.sex_gender and patient.postal == self.postal:
            self.days_symptomatic = patient.days_symptomatic
            self.state = patient.state
            self.temps.append(patient.temps[0])
        else:
            print(str(patient))
            raise AssertionError


def stage_four(input_filename, output_filename):
    # out file
    out_file = open(output_filename, 'w', encoding='utf-8')
    patient_dic = dict()
    # Open file
    with open(input_filename, "r", encoding='utf-8') as fileHandler:
        # Read each line in loop
        for line in fileHandler:
            # As each line (except last one) will contain new line character, so strip that
            line = line.strip()
            line_list = line.split('\t')

            new_patient = Patient(line_list[1], line_list[2], line_list[3], line_list[4], line_list[5],
                                  line_list[6], line_list[7], line_list[8])

            # print(str(new_patient))
            if line_list[1] in patient_dic:
                existing_patient = patient_dic[line_list[1]]
                existing_patient.update(new_patient)
                patient_dic[line_list[1]] = existing_patient
            else:
                patient_dic[line_list[1]] = new_patient

    for key, value in sorted(patient_dic.items()):
        out_file.write(str(value) + '\n')

    out_file.close()
    return patient_dic


def round_nearest5(x, base=5):
    return base * round(x/base)


def fatality_by_age(patient_dic):
    # {age: [deaths, recoveries]}
    prob_fatality = {}
    for key, value in sorted(patient_dic.items()):
        round_age = round_nearest5(value.age)
        if round_age in prob_fatality:
            if value.state == 'R':
                prob_fatality[round_age] = [prob_fatality[round_age][0], prob_fatality[round_age][1] + 1]
            if value.state == 'D':
                prob_fatality[round_age] = [prob_fatality[round_age][0] + 1, prob_fatality[round_age][1]]
        else:
            if value.state == 'R':
                prob_fatality[round_age] = [0, 1]
            if value.state == 'D':
                prob_fatality[round_age] = [1, 0]
    print(prob_fatality)
    age = []
    probability = []
    for key, value in sorted(prob_fatality.items()):
        age.append(key)
        probability.append(value[0]/(value[0]+value[1]))
    plt.plot(age, probability)
    plt.ylim((0, 1.2))
    plt.xlabel('Age')
    plt.ylabel('Deaths / (Deaths+Recoveries)')
    plt.title('Probability of death vs age')
    plt.savefig('fatality_by_age.png')

    return probability

# p = Patient('0', '0', '42', 'Woman', 'H3Z2B5', 'I', '102.2', '12')
# p1 = Patient('0', '1', '42', 'F', 'H3Z', 'I', '40,0 C', '13')
# p.update(p1)
#
# print(str(p))
d = stage_four("3000.3.out.txt", "3000.4.out.txt")
print(len(d))
print(fatality_by_age(d))
