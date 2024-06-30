import { useEffect, useState } from "react";
import { Input } from "@/shared/components/Input";
import { Alert } from "@/shared/components/Alert";
import { Button } from "@/shared/components/Button";
import { login } from "./api";
import { useAuthDispatch } from "@/shared/state/context";
import { Link, useNavigate } from "react-router-dom";

export function Login({ onLoginSuccess }) {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [apiProgress, setApiProgress] = useState();
  const [errors, setErrors] = useState({});
  const [generalError, setGeneralError] = useState();
  const navigate = useNavigate();
  const dispatch = useAuthDispatch();

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        email: undefined,
      };
    });
  }, [email]);

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        password: undefined,
      };
    });
  }, [password]);

  const onSubmit = async (event) => {
    event.preventDefault();
    setGeneralError();
    setApiProgress(true);

    try {
      const response = await login({
        email,
        password,
      });
      dispatch({ type: "login-success", data: response.data });
      navigate("/");
    } catch (axiosError) {
      console.log(axiosError);
      if (axiosError.response?.data) {
        if (axiosError.response.data.status === "400") {
          setErrors(axiosError.response.data.errors);
        } else {
          setGeneralError(axiosError.response.data.message);
        }
      } else {
        setGeneralError("Genel Hata");
      }
    } finally {
      setApiProgress(false);
    }
  };

  return (
    <div className="container">
      <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
        <form className="card" onSubmit={onSubmit}>
          <div className="text-center card-header">
            <h1>Giriş Yap</h1>
          </div>
          <div className="card-body">
            <Input
              id="email"
              label="Email"
              error={errors.email}
              onChange={(event) => setEmail(event.target.value)}
            />

            <Input
              id="password"
              label="Şifre"
              error={errors.password}
              onChange={(event) => setPassword(event.target.value)}
              type="password"
            />
            {generalError && <Alert styleType="danger">{generalError}</Alert>}
            <div className="text-center">
              <Button disabled={!email || !password} apiProgress={apiProgress}>
                Giriş Yap
              </Button>
            </div>
          </div>
          <div className="card-footer text-center">
            <Link to="/password-reset/request">Forget Password?</Link>
          </div>
        </form>
      </div>
    </div>
  );
}
