import {IErrorMessage} from '../Interfaces/IErrorMessage';

export class ErrorMessage implements ErrorMessage{
    errorMessage: string;
    valid: boolean;

    constructor(errorMessage: string, valid: boolean) {
        this.errorMessage = errorMessage;
        this.valid = valid;
    }
}
