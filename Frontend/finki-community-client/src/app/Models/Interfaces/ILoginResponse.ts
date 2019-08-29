import {Authorization} from '../Enumeration/Authorization';

export interface ILoginResponse {
    valid: boolean;
    expiresIn: string;
    idToken: string;
    role: Authorization;
    username: string;
    errorMessage?: string;
}
