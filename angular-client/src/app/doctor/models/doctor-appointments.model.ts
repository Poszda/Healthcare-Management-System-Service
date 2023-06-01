import { AppointmentStatus } from "./appointment-status.enum"

export interface DoctorAppointment{
    id: number
    duration: number
    date : string
    time : string
    procedureName : string
    patientId : number
    lastName : string
    firstName : string
    phone : string
    age : string
    status : AppointmentStatus
}

export interface DoctorAppointmentTabelData{
    id: number
    date: string;
    time : string
    procedureName : string 
    patientId : number
    name : string 
    phone : string
    age : string
    status : AppointmentStatus 
}