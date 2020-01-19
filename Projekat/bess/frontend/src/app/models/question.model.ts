import { Answer } from "./answer.model";

export class Question{
    public questionId:string;
    public text: string;
    public answers:Answer[];

    constructor(text:string,questionId:string,answers:Answer[])
    {
        this.text = text;
        this.questionId = text;
        this.answers = answers;
    }
}