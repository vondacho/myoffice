import {Action, State, StateContext} from '@ngxs/store';
import {Problem} from 'shared/model/problem/problem';
import {DomainProblemOccured, TechnicalProblemOccured} from '../action/problem-events';

@State<Problem>({
  name: 'lastProblem',
  defaults: undefined
})
export class LastProblemState {

  @Action(DomainProblemOccured)
  public domainProblem(ctx: StateContext<Problem>, {problem}: DomainProblemOccured) {
    ctx.patchState(problem);
  }

  @Action(TechnicalProblemOccured)
  public technicalProblem(ctx: StateContext<Problem>, {problem}: TechnicalProblemOccured) {
    ctx.patchState(problem);
  }
}
