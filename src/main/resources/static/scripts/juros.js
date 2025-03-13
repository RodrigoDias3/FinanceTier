const faixasRetencao = [
    { limite: 820, taxa: 0, abater: 0 },
    { limite: 935, taxa: 0, abater: 0 },
    { limite: 989, taxa: 0, abater: 0 },
    { limite: 1125, taxa: 0, abater: 0 },
    { limite: 1175, taxa: 0, abater: 0 },
    { limite: 1769, taxa: 3.75, abater: 44.07 },
    { limite: 2057, taxa: 8.00, abater: 119.25 },
    { limite: 2408, taxa: 10.65, abater: 173.76 },
    { limite: 3201, taxa: 19.36, abater: 383.44 },
    { limite: 5492, taxa: 40.05, abater: 1045.80 },
    { limite: 20021, taxa: 44.95, abater: 1314.64 },
    { limite: Infinity, taxa: 47.17, abater: 1760.10 }
];

function calcularRetencao() {
    const salario = parseInt(document.getElementById("salarioInput").value);
    if (isNaN(salario) || salario <= 0) {
        alert("Por favor, insira um salário válido.");
        return;
    }

    let taxa = 0, abater = 0;
    for (let faixa of faixasRetencao) {
        if (salario <= faixa.limite) {
            taxa = faixa.taxa;
            abater = faixa.abater;
            break;
        }
    }

    const valorRetidoMensal = salario * (taxa / 100) - abater;
    const segurancaSocial = salario * (11.0 / 100);
    const bruto = salario + 211.20;
    const liquido = salario - valorRetidoMensal - segurancaSocial;
    const total = liquido + 211.20;

    document.getElementById("salario").innerText = salario.toFixed(2);
    document.getElementById("alimentacao").innerText = '+211,20€';
    document.getElementById("sbruto").innerText = bruto.toFixed(2);

    document.getElementById("taxaMensal").innerText = `${taxa.toFixed(2)}%`;
    document.getElementById("valorMensal").innerText = valorRetidoMensal.toFixed(2);
    document.getElementById("ss").innerText = segurancaSocial.toFixed(2);

    document.getElementById("sliquido").innerText = liquido.toFixed(2);
    document.getElementById("alimentacaol").innerText = '+211,20€';
    document.getElementById("total").innerText = total.toFixed(2);

    const ctx = document.getElementById('doughnut').getContext('2d');
    const doughnut = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Salário Líquido', 'Segurança Social', 'Retenção IRS'],
            datasets: [{
                data: [total, segurancaSocial, valorRetidoMensal],
                backgroundColor: [
                    '#33ADD7', // Salário Líquido - Azul
                    '#F29781', // Segurança Social - Vermelho
                    '#EBB4A3'  // Retenção IRS - Amarelo
                ],
                borderColor: [
                    '#33ADD7', // Salário Líquido - Azul
                    '#F29781', // Segurança Social - Vermelho
                    '#EBB4A3'  // Retenção IRS - Amarelo
                ],
                borderWidth: 1
            }]
        },
        options: {
            plugins: {
                legend: {
                    display: true,
                    position: 'bottom'
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            const label = context.label || '';
                            const value = context.raw || 0;
                            const percentage = ((value / bruto) * 100).toFixed(2);
                            return `${label}: €${value.toFixed(2)} (${percentage}%)`;
                        }
                    }
                }
            }
        }
    });
}

function atualizaGraico() {

}