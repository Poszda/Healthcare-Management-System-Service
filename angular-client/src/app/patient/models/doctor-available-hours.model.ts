export interface DoctorsAvailableHours{
    doctorId : number,
    dates : AvailableHours[]
}

interface AvailableHours{
    date : string
    hours : string[]
}