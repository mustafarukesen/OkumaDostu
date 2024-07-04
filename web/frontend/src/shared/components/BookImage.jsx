import defaultBookImage from "@/assets/emptyBookImage.jpg";

export function BookImage({ width, tempImage, image }) {
  const bookImage = image ? image : defaultBookImage;

  return (
    <img
      src={tempImage || bookImage}
      width={width}
      height={width}
      className="rounded float-start"
      onError={({ target }) => {
        target.src = defaultProfileImage;
      }}
    />
  );
}
