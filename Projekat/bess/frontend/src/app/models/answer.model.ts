export class Answer{
    public answerId : number;
    public text: string;
    public questionId:string;

    constructor(answerId:number,text:string,questionId:string)
    {
        this.answerId = answerId;
        this.text = text;
        this.questionId = text;
    }
}