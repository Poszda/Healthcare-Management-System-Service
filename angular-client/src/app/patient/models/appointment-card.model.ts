export interface AppointmentCard{
    id : number,
    doctorFirstName : string,
    doctorLastName : string,
    doctorSpeciality: string,
    procedureName : string,
    hospitalName : string,
    dateTime :string,
    price : number,
    duration:number
    diagnosticId? : number
}