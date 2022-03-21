import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.scss']
})
export class UpdateQuizComponent implements OnInit {

  qId=0;
  quiz:any;
  categories:any;
  constructor(private _route: ActivatedRoute,private _quiz: QuizService,private _cat: CategoryService,private _router: Router) { }

  ngOnInit(): void {
    this.qId=this._route.snapshot.params.qid;
    this._quiz.getQuiz(this.qId).subscribe((data: any)=>{
      this.quiz=data;
      console.log(this.quiz);
    },
    (error) => {
      console.log(error);
      
    }
    );
    this._cat.categories().subscribe((data: any)=>{
      this.categories=data;
    },
    (error) => {
      alert(error);
    }
    )
  }

  public updateData(){
    //validate
    this._quiz.updateQuiz(this.quiz).subscribe((data: any)=>{
      Swal.fire('Success','Quiz Updated','success').then((e)=>{
        this._router.navigate(['/admin/quizzes']);
      });
    },
    (error)=>{
      Swal.fire('Error','Error in updating quiz','error');
    });
  }

}
