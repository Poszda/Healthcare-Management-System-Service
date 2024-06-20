export interface AppointmentSuggestion{
        doctorId : number,
        doctorName : string,
        doctorSpeciality : string,
        hospitalName : string,
        profileImage?:string,
        date : string,
        hours : string[]
}