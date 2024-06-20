export interface AppointmentCard{
    id : number,
    doctorId : number,
    doctorFirstName : string,
    doctorLastName : string,
    doctorSpeciality: string,
    profileImage? : string,
    procedureName : string,
    hospitalName : string,
    dateTime :string,
    price : number,
    duration : number,
    diagnosticId? : number
}