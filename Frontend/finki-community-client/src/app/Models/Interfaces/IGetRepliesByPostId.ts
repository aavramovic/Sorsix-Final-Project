    import {IThread} from './IThread';

export interface IGetRepliesByPostId {
    post: IThread;
    replies: IThread[];
}
