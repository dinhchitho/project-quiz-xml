import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quiz-question',
  templateUrl: './view-quiz-question.component.html',
  styleUrls: ['./view-quiz-question.component.scss']
})
export class ViewQuizQuestionComponent implements OnInit {

  qId:any;
  qTitle:any;
  questions:any=[];

  constructor(private _route:ActivatedRoute,private _question:QuestionService,private _snack:MatSnackBar) { }

  ngOnInit(): void {
    this.qId=this._route.snapshot.params.qid;
    this.qTitle=this._route.snapshot.params.title;
    this._question.getQuestionsOfQuiz(this.qId).subscribe((data:any) => {
      console.log(data);
      this.questions=data;
    },
    (error) => {
      console.log(error);
    }
    );
  }

  deleteQuestion(questionId:any) {
    Swal.fire({
      icon:'info',
      showCancelButton:true,
      confirmButtonText:'Delete',
      title:'Are you sure you want to delete this question?'
    }).then((result)=>{
      if(result.isConfirmed){
        this._question.deleteQuiz(questionId).subscribe((data: any)=>{
          this._snack.open('Question Deleted','',{
            duration: 3000,
          });
          this.questions=this.questions.filter((q:any)=>q.quesId!=questionId);
        },
        (error)=>{
          this._snack.open('Error in deleting questions','',{
            duration: 3000,
          });
        });
      };
    });
  }

}
