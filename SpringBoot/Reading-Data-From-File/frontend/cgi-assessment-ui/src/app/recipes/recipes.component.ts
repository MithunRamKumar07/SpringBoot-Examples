import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit {
  recipesDetails : String ;
  ingredient: String;
  @Output('onError') onError: EventEmitter<Response> = new EventEmitter();
  errorResponse: String;
  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
  }

  getRecipes(): Observable<String> {
    this.clearRecipes();
    console.log("Backend call")
    // @ts-ignore
    return this.http.get(`${environment.apiEndpoint}/assessment/recipes/getAll`)
        .subscribe((response)=>{
            // @ts-ignore
          this.handleRecipeResponse(response)
      console.log(response)
    });
  }

  private handleRecipeResponse(response: String) {
    this.recipesDetails = response;


  }

  clearRecipes() {
    // @ts-ignore
    this.recipesDetails = null
    // @ts-ignore
    this.errorResponse = null
  }

  getRecipesByIngredient(){
    this.clearRecipes();
    const ingredients = this.ingredient.split(",");
    return this.http.post(`${environment.apiEndpoint}/assessment/recipes/getByIngredients`,ingredients)
      .subscribe((response)=>{
        // @ts-ignore
        this.handleRecipeResponse(response)
        console.log(response)
      },
        error => this.handleError(error));
  }

  private handleError(error: string) {
    this.errorResponse = error;

  }
}
