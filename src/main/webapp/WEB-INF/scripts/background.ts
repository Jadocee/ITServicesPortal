window.onload = async (ev) => {
  let loginBtn = document.querySelector("#LoginButton")
  if (loginBtn != null) {
    loginBtn.addEventListener("click", async (ev) => {
      window.location.href = "./login";
    }, false);
  }
}