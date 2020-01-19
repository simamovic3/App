export class User{
    userId:number;
    name: string;
    lastName: string;
    email: string;
    username: string;
    password: string;
    age: number;
    role: number;

    get getRole(): number {
        return this.role;
    }

}