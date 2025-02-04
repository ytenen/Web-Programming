import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import LoginForm from './components/LoginForm';
import RegisterForm from './components/RegisterForm';
import MainPage from './MainPage';

const App = () => {
  return (
      <div>
          <Router>
              <Switch>
                  <Route
                      exact
                      path="/"
                      component={(props) => <LoginForm {...props} onSwitch={() => props.history.push('/register')} />}
                  />
                  <Route
                      path="/register"
                      component={(props) => <RegisterForm {...props} onSwitch={() => props.history.push('/')} />}
                  />
                  <Route path="/main" component={MainPage} />
              </Switch>
          </Router>
      </div>
  );
};

export default App;
