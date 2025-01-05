package wsb.studenci.blog.model.request.auth;

public class ChangePasswordPostRequest
{
    private final String oldPassword;
    private final String newPassword;
    private final String newPasswordConfirmation;

    public ChangePasswordPostRequest(
        String oldPassword,
        String newPassword,
        String newPasswordConfirmation
    ) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordConfirmation = newPasswordConfirmation;
    }

    public String getOldPassword()
    {
        return oldPassword;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public String getNewPasswordConfirmation()
    {
        return newPasswordConfirmation;
    }
}
