export interface IThread {
    id: number;

    courseName: string;
    courseCode: string;

    timeOfPost: Date;
    noOfLikes: number;
    noOfComments: number;
    content: string;
    title: string;

    username: string;
    sex: string;
    role: string;
    isLiked: boolean;

    replies?: IThread[];
    repliedTo: number;
}
