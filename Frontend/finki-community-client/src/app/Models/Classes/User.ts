import {Authorization} from '../Enumeration/Authorization';

export class User {
  private username: string;
  private firstName: string;
  private lastName: string;
  private password: string;
  private authorization: Authorization;
  private email: string;
  private pictureUrl: string;
  private numberOfPosts: string;
}
