import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrls: ['./load-quiz.component.scss']
})
export class LoadQuizComponent implements OnInit {

  catId:any;
  quizzes:any;

  constructor(private _route: ActivatedRoute,private _quiz:QuizService) { }

  ngOnInit(): void {
    this._route.params.subscribe((params: any) => {
      this.catId=params.catId;
      if(this.catId==0){
        console.log('Load all the quiz');
        this._quiz.getActiveQuizzes().subscribe((data: any)=>{
          this.quizzes=data;
        },
        (error)=>{
          alert(error);
        }
        );
      }else{
        console.log('Load specific quiz');
        this._quiz.getActiveQuizzesOfCategory(this.catId).subscribe((data: any)=>{
          this.quizzes=data;
        },
        (error)=>{
          alert(error);
        }
        );
      }
    });
  }

}
