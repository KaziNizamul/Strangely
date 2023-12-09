import { combineReducers } from 'redux';
import loginSignupReducer from '../../components/organisms/LoginSignup/data/LoginSignup.reducer';
import postReducer from '../../pages/Postpage/data/PostPage.reducer';

const rootReducer = combineReducers({
  loginSignupReducer,
  postReducer,
});

export default rootReducer;
