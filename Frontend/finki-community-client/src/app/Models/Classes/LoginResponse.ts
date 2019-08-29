import {ILoginResponse} from '../Interfaces/ILoginResponse';
import {Authorization} from '../Enumeration/Authorization';
import {IErrorMessage} from '../Interfaces/IErrorMessage';

export class LoginResponse implements ILoginResponse, IErrorMessage {
    expiresIn: string;
    idToken: string;
    role: Authorization;
    valid: boolean;
    errorMessage: string;
    username: string;

    constructor(expiresIn: string, idToken: string, role: Authorization, valid: boolean, errorMessage?: string) {
        this.expiresIn = expiresIn;
        this.idToken = idToken;
        this.role = role;
        this.valid = valid;
        this.errorMessage = errorMessage;
    }
}
