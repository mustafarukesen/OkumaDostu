export function Input(props) {
  const {
    id,
    label,
    error,
    onChange,
    type,
    defaultValue,
    placeholder,
    labelColor,
  } = props;

  return (
    <div className="mb-3">
      <label htmlFor={id} className="form-label" style={{ color: labelColor }}>
        {label}
      </label>
      <input
        id={id}
        className={error ? "form-control is-invalid" : "form-control"}
        onChange={onChange}
        type={type}
        defaultValue={defaultValue}
        placeholder={placeholder}
      />
      <div className="invalid-feedback">{error}</div>
    </div>
  );
}
