export interface SpecialityFrequency{
    speciality : string,
    appointments : number
}

export interface SpecialityFrequencyPercentage extends SpecialityFrequency{
    percentage : number
}
