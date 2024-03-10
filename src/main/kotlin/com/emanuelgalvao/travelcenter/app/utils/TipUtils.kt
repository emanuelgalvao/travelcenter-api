package com.emanuelgalvao.travelcenter.app.utils

object TipUtils {

    private val tips = arrayListOf(
        "Faça uma lista de itens essenciais antes de empacotar.",
        "Verifique as restrições de bagagem antes de voar.",
        "Faça uma cópia dos seus documentos importantes.",
        "Aprenda algumas frases básicas no idioma local.",
        "Pesquise sobre a cultura e costumes do destino antes de viajar.",
        "Use roupas confortáveis para os longos trajetos.",
        "Mantenha-se hidratado durante os voos.",
        "Tenha um seguro de viagem adequado.",
        "Informe-se sobre os requisitos de visto com antecedência.",
        "Descubra a melhor época para visitar o seu destino.",
        "Reserve acomodações com antecedência para garantir disponibilidade.",
        "Experimente a culinária local.",
        "Esteja ciente das moedas e taxas de câmbio.",
        "Use aplicativos de viagem para ajudar na organização.",
        "Mantenha-se flexível com seu itinerário.",
        "Não se esqueça de levar um adaptador de energia universal.",
        "Esteja ciente das atrações turísticas populares e evite as multidões.",
        "Explore o destino a pé ou de bicicleta sempre que possível.",
        "Interaja com os moradores locais para obter dicas e recomendações.",
        "Mantenha seus objetos de valor seguros.",
        "Faça backup regularmente de suas fotos e documentos importantes.",
        "Informe-se sobre as opções de transporte público no destino.",
        "Tenha um plano de emergência em caso de imprevistos.",
        "Pesquise sobre os costumes de gorjeta no país visitado.",
        "Use protetor solar e repelente de insetos, conforme necessário.",
        "Experimente atividades ao ar livre, como caminhadas ou mergulho.",
        "Evite andar sozinho à noite em áreas desconhecidas.",
        "Mantenha uma mente aberta e respeite as diferenças culturais.",
        "Participe de tours guiados para aprender mais sobre o destino.",
        "Conheça a história e os pontos de referência do local.",
        "Fique atento a promoções e descontos para economizar dinheiro.",
        "Use um mapa offline ou um aplicativo de navegação em áreas desconhecidas.",
        "Informe-se sobre as vacinas necessárias para o destino.",
        "Respeite o meio ambiente e evite deixar lixo nas áreas naturais.",
        "Experimente meios de transporte locais, como trens ou tuk-tuks.",
        "Informe-se sobre as leis e regulamentos locais.",
        "Mantenha-se atualizado sobre eventos e festivais especiais.",
        "Esteja ciente das normas de vestimenta em locais religiosos.",
        "Tenha cuidado com golpes turísticos comuns.",
        "Use um seguro de saúde internacional para cobertura médica.",
        "Mantenha um orçamento e controle seus gastos durante a viagem.",
        "Aproveite as vistas panorâmicas e os mirantes do destino.",
        "Experimente pratos locais em mercados de rua.",
        "Seja respeitoso ao fotografar pessoas e locais sagrados.",
        "Mantenha-se seguro em praias e áreas de natação.",
        "Compre lembranças autênticas e apoie os negócios locais.",
        "Descubra trilhas e rotas alternativas menos turísticas.",
        "Esteja preparado para mudanças climáticas repentinas.",
        "Use aplicativos de tradução para se comunicar em diferentes idiomas.",
        "Divirta-se e aproveite cada momento da sua viagem!"
    )

    fun getRandomTip(): String {
        val index = (0..tips.size).random()
        return tips[index]
    }
}