import {Authorization} from '../Enumeration/Authorization';

export interface ILoginResponse {
    valid: boolean,
    expiresIn: string,
    idToken: string,
    role: Authorization,
    errorMessage?: string
}
