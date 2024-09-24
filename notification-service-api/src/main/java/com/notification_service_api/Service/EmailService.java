package com.notification_service_api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.notification_service_api.dto.PayDTO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(PayDTO payDTO) throws MessagingException {
        String subject = "Tu compra ha sido finalizada exitosamente - Pedido #" + payDTO.getOrderId();
        String htmlMessage = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Confirmación de Compra</title>
                <style>
                    body { font-family: Arial, sans-serif; color: #333; }
                    .container { width: 100%; max-width: 600px; margin: 0 auto; }
                    .header { background-color: #4CAF50; color: white; padding: 10px; text-align: center; }
                    .content { padding: 20px; }
                    .footer { background-color: #f1f1f1; color: #333; text-align: center; padding: 10px; font-size: 12px; }
                    table { width: 100%; border-collapse: collapse; }
                    th, td { text-align: left; padding: 8px; }
                    th { background-color: #f2f2f2; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>¡Gracias por tu compra, {userName}!</h1>
                    </div>
                    <div class="content">
                        <p>Hola {userName},</p>
                        <p>Nos complace informarte que tu compra ha sido procesada exitosamente. A continuación, te proporcionamos los detalles de tu pedido:</p>
                        <table>
                            <tr>
                                <th>Número de Pedido:</th>
                                <td>{orderId}</td>
                            </tr>
                            <tr>
                                <th>Fecha de Pedido:</th>
                                <td>{orderDate}</td>
                            </tr>
                            <tr>
                                <th>Total Pagado:</th>
                                <td>${totalAmount}</td>
                            </tr>
                            <tr>
                                <th>Estado del Pedido:</th>
                                <td>{orderStatus}</td>
                            </tr>
                        </table>
                        <h3>Detalles del Cliente</h3>
                        <p>Dirección: {userAddress}</p>
                        <p>Teléfono: {userPhone}</p>
                    </div>
                    <div class="footer">
                        <p>Gracias por comprar con nosotros.</p>
                    </div>
                </div>
            </body>
            </html>
        """;

        htmlMessage = htmlMessage
                .replace("{userName}", payDTO.getUserName())
                .replace("{orderId}", String.valueOf(payDTO.getOrderId()))
                .replace("{orderDate}", payDTO.getOrderDate().toString())
                .replace("{totalAmount}", String.format("%.2f", payDTO.getTotalAmount()))
                .replace("{orderStatus}", payDTO.getOrderStatus().name())
                .replace("{userAddress}", payDTO.getUserAddress())
                .replace("{userPhone}", payDTO.getUserPhone());

        sendOrderConfirmation(payDTO.getUserEmail(), subject, htmlMessage);
    }

    private void sendOrderConfirmation(String toEmail, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(toEmail);
        helper.setFrom(fromEmail);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}

