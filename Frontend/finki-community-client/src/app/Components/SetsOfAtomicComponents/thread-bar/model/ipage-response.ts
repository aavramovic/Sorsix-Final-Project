import {Thread} from '../../../../Models/Classes/Thread';

export interface IPageResponse {
    count: number;
    data: Array<Thread>;
}
