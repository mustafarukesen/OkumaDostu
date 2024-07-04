import { Button } from "@/shared/components/Button";
import { activateUser } from "./api";
import { Alert } from "@/shared/components/Alert";
import { Input } from "@/shared/components/Input";
import { Spinner } from "@/shared/components/Spinner";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export function Activation() {
  const [activation, setActivation] = useState();
  const [apiProgress, setApiProgress] = useState();
  const [errors, setErrors] = useState({});
  const [generalError, setGeneralError] = useState();
  const navigate = useNavigate();

  const handleActivation = async (token) => {
    setApiProgress(true);
    try {
      await activateUser(activation);
      console.log("Kullanıcı aktivasyonu başarıyla gerçekleştirildi.");
      navigate("/");
    } catch (error) {
      console.error("Aktivasyon sırasında bir hata oluştu:", error);
    } finally {
      setApiProgress(false);
    }
  };

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        activation: undefined,
      };
    });
  }, [activation]);

  return (
    <>
      {apiProgress && (
        <Alert styleType="secondary" center>
          <Spinner />
        </Alert>
      )}
      <div
        className="card"
        style={{
          marginLeft: "100px",
          marginRight: "100px",
          marginTop: "100px",
        }}
      >
        <Input
          id="password"
          label="Aktivasyon kodunu girin"
          error={errors.password}
          onChange={(event) => setActivation(event.target.value)}
          type="number"
        />
        {generalError && <Alert styleType="danger">{generalError}</Alert>}
        <div className="card-header text-center">
          <Button
            disabled={!activation}
            apiProgress={apiProgress}
            onClick={() => handleActivation()}
          >
            Aktive Et
          </Button>
        </div>
      </div>
    </>
  );
}
