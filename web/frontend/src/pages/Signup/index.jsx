import { useEffect, useMemo, useState } from "react";
import { signUp } from "./api";
import { Input } from "@/shared/components/Input";
import { Alert } from "@/shared/components/Alert";
import { Button } from "@/shared/components/Button";
import "./signupStyle.css";
import { useNavigate } from "react-router-dom";

export function SignUp() {
  const [firstName, setFirstName] = useState();
  const [lastName, setLastName] = useState();
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [passwordRepeat, setPasswordRepeat] = useState();
  const [apiProgress, setApiProgress] = useState();
  const [successMessage, setSuccessMessage] = useState();
  const [errors, setErrors] = useState({});
  const [generalError, setGeneralError] = useState();
  const navigate = useNavigate();

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        firstName: undefined,
      };
    });
  }, [firstName]);

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        lastName: undefined,
      };
    });
  }, [lastName]);

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

  function navigateInNewTab(url) {
    window.open(url, "_blank");
  }

  const onSubmit = async (event) => {
    event.preventDefault();
    setSuccessMessage();
    setGeneralError();
    setApiProgress(true);

    try {
      const response = await signUp({
        firstName,
        lastName,
        email,
        password,
      });
      setSuccessMessage(response.data.message);
      navigateInNewTab("http://localhost:1080");
      navigate("/")
    } catch (axiosError) {
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

  const passwordRepeatError = useMemo(() => {
    if (password && password !== passwordRepeat) return "Yanlış Şifre";
    return "";
  }, [password, passwordRepeat]);

  return (
    <div className="container">
      <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
        <form className="card" onSubmit={onSubmit}>
          <div className="text-center card-header">
            <h1>Kayıt Ol</h1>
          </div>
          <div className="card-body">
            <Input
              id="firstName"
              label="İsim"
              error={errors.firstName}
              onChange={(event) => setFirstName(event.target.value)}
            />
            <Input
              id="lastName"
              label="Soyisim"
              error={errors.lastName}
              onChange={(event) => setLastName(event.target.value)}
            />
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

            <Input
              id="passwordRepeat"
              label="Şifre Tekrarı"
              error={passwordRepeatError}
              onChange={(event) => setPasswordRepeat(event.target.value)}
              type="password"
            />

            {successMessage && <Alert>{successMessage}</Alert>}
            {generalError && <Alert styleType="danger">{generalError}</Alert>}

            <div className="text-center">
              <Button
                disabled={!password || password !== passwordRepeat}
                apiProgress={apiProgress}
              >
                Kayıt Ol
              </Button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}
