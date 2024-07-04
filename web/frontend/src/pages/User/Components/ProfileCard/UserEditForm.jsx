import { Alert } from "@/shared/components/Alert";
import { Button } from "@/shared/components/Button";
import { Input } from "@/shared/components/Input";
import { useAuthDispatch, useAuthState } from "@/shared/state/context";
import { useState } from "react";
import { updateUser } from "./api";

export function UserEditForm({ setEditMode, setTempImage }) {
  const authState = useAuthState();
  const [newFirstName, setNewFirstName] = useState(authState.firstName);
  const [newLastName, setNewLastName] = useState(authState.lastName);
  const [newDateOfBirth, setNewDateOfBirth] = useState(authState.dateOfBirth);
  const [apiProgress, setApiProgress] = useState(false);
  const [errors, setErrors] = useState({});
  const [generalError, setGeneralError] = useState();
  const dispatch = useAuthDispatch();
  const [newImage, setNewImage] = useState();

  const onChangeFirstName = (event) => {
    setNewFirstName(event.target.value);
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        firstName: undefined,
      };
    });
  };

  const onChangeLastName = (event) => {
    setNewLastName(event.target.value);
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        lastName: undefined,
      };
    });
  };

  const onChangeDateOfBirth = (event) => {
    setNewDateOfBirth(event.target.value);
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        dateOfBirth: undefined,
      };
    });
  };

  const onClickCancel = () => {
    setEditMode(false);
    setNewFirstName(authState.firstName);
    setNewLastName(authState.lastName);
    setNewDateOfBirth(authState.dateOfBirth);
    setNewImage();
    setTempImage();
  };

  const onSelectImage = (event) => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        image: undefined,
      };
    });

    if (event.target.files.length < 1) return;
    const file = event.target.files[0];
    const fileReader = new FileReader();

    fileReader.onloadend = () => {
      const data = fileReader.result;
      setNewImage(data);
      setTempImage(data);
    };

    fileReader.readAsDataURL(file);
  };

  const onSubmit = async (event) => {
    event.preventDefault();
    setApiProgress(true);
    setErrors({});
    setGeneralError();
    try {
      const { data } = await updateUser(authState.id, {
        firstName: newFirstName,
        lastName: newLastName,
        dateOfBirth: newDateOfBirth,
        image: newImage,
      });
      dispatch({
        type: "user-update-success",
        data: {
          firstName: data.user.firstName,
          lastName: data.user.lastName,
          dateOfBirth: data.user.dateOfBirth,
          image: data.user.image,
        },
      });
      setEditMode(false);
    } catch (axiosError) {
      if (axiosError.response?.data) {
        if (axiosError.response.data.status === "400") {
          setErrors(axiosError.response.data.validationErrors);
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
    <form onSubmit={onSubmit}>
      <Input
        label="İsim"
        defaultValue={authState.firstName}
        onChange={onChangeFirstName}
        error={errors.firstName}
      />
      <Input
        label="Soyisim"
        defaultValue={authState.lastName}
        onChange={onChangeLastName}
        error={errors.lastName}
      />
      <Input
        label="Doğum Tarihi"
        type="date"
        defaultValue={authState.dateOfBirth}
        onChange={onChangeDateOfBirth}
        error={errors.dateOfBirth}
      />
      <Input
        label="Profile Image"
        type="file"
        onChange={onSelectImage}
        error={errors.image}
      />
      {generalError && <Alert styleType="danger">{generalError}</Alert>}
      <Button apiProgress={apiProgress} type="submit">
        Save
      </Button>
      <div className="d-inline m-1"></div>
      <Button
        styleType="outline-secondary"
        onClick={onClickCancel}
        type="button"
      >
        Cancel
      </Button>
    </form>
  );
}
