import matplotlib.pyplot as plt


class Patient:
    def __init__(self, num, day_diagnosed, age, sex_gender, postal, state, temps, days_symptomatic):
        self.num = int(num)
        self.day_diagnosed = int(day_diagnosed)
        self.age = int(age)
        if sex_gender.startWith('M') or sex_gender.startWith('H') or sex_gender == 'BOY':
            self.sex_gender = 'M'
        elif sex_gender.startWith('F') or sex_gender.startWith('W'):
            self.sex_gender = 'F'
        else:
            self.sex_gender = 'X'
        self.postal = self.validate_postal(postal)
        self.state = state
        self.temps = [self.parse_temps(temps)]
        self.days_symptomatic = days_symptomatic

    def parse_temps(self, temps):
        temps.replace(',', '.')
        temps.replace(' ', '')
        new = ''
        for letter in temps:
            if not (letter.isalpha()):
                new += letter
        return float(new)

    def validate_postal(self, postal):
        if len(postal) < 3 or postal == 'N.A.':
            return '000'
        if postal[0] == 'H' and postal[1].isdigit() and postal[2].isalpha():
            return postal[:3]
        return '000'

    def __str__(self):
        return self.num + '\t' + self.age + '\t' + self.sex_gender + '\t' + self.postal + '\t' + self.day_diagnosed + \
               '\t' + self.state + '\t' + self.days_symptomatic + '\t' + ';'.join(self.temps)

    def update(self, patient):
        if patient.num == self.num and patient.sex_gender == self.sex_gender and patient.postal == self.postal:
            self.days_symptomatic = patient.days_symptomatic
            self.state = patient.state
            self.temps.append(patient.temps[0])
        else:
            raise AssertionError


def stage_four(input_filename, output_filename):
    # out file
    out_file = open(output_filename, 'w', encoding='utf-8')
    patient_dic = {}
    # Open file
    with open(input_filename, "r", encoding='utf-8') as fileHandler:
        # Read each line in loop
        for line in fileHandler:
            # As each line (except last one) will contain new line character, so strip that
            line = line.strip()
            line_list = line.split('\t')

            new_patient = Patient(line_list[1], line_list[2], line_list[3], line_list[4], line_list[5],
                                  line_list[6], line_list[7], line_list[8])
            if line_list[1] in patient_dic:
                patient_dic[line_list[1]] = patient_dic[line_list[1]].update(new_patient)
            else:
                patient_dic[line_list[1]: new_patient]

    for key, value in sorted(patient_dic.iteritems()):
        out_file.write(str(value) + '\n')

    out_file.close()
    return patient_dic


def round_nearest5(x, base=5):
    return base * round(x/base)


def fatality_by_age(patient_dic):
    # {age: [deaths, recoveries]}
    prob_fatality = {}
    for key, value in sorted(patient_dic.iteritems()):
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

    age = []
    probability = []
    for key, value in sorted(prob_fatality.iteritems()):
        age.append(key)
        probability.append(value[0]/(value[0]+value[1]))
    plt.plot(age, probability)
    plt.ylim((0, 1.2))
    plt.xlabel('Age')
    plt.ylabel('Deaths / (Deaths+Recoveries)')
    plt.title('Probability of death vs age')
    plt.savefig('fatality_by_age.png')

    return probability
