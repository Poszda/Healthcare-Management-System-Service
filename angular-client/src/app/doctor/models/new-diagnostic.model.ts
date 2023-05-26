export interface newDiagnostic{
    appointmentId : number;
    diagnostic : string
    description : string
    medications : Medicine[]
}

interface Medicine{
    name : string
    numberOfDays : number
    dose : string
}


