import { initializeApp } from "https://www.gstatic.com/firebasejs/11.6.1/firebase-app.js";
import { getAuth, GoogleAuthProvider, signInWithPopup, signOut, onAuthStateChanged } from "https://www.gstatic.com/firebasejs/11.6.1/firebase-auth.js";
import { getFirestore, collection, addDoc, query, where, getDocs, onSnapshot, serverTimestamp, doc, deleteDoc } from "https://www.gstatic.com/firebasejs/11.6.1/firebase-firestore.js";

const firebaseConfig = {
    apiKey: "AIzaSyD0rC3RYKVEutV4lg1UMehtavZmATEE3gk",
    authDomain: "palestras-ac51e.firebaseapp.com",
    projectId: "palestras-ac51e",
    storageBucket: "palestras-ac51e.appspot.com",
    messagingSenderId: "961901054844",
    appId: "1:961901054844:web:15118d30a8d2fbdac032f9"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const db = getFirestore(app);
const provider = new GoogleAuthProvider();
const registrationsCollection = collection(db, "artifacts/default-lecture-app/public/data/registrations");

const TOTAL_SPOTS = 40;
const MESSAGE_COLORS = {
    red: "text-red-600",
    green: "text-green-600",
    orange: "text-orange-500"
};

const lecturesByDay = [
    {
        day: 1,
        lectures: [
            { id: "medicina-dia1", title: "Medicina", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala A<br><strong>Palestrante:</strong> Wagner Moneda Telini" },
            { id: "psicologia-dia1", title: "Psicologia", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala B<br><strong>Palestrante:</strong> Carol Hamparian" },
            { id: "Direito-dia1", title: "Direito", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala C<br><strong>Palestrante:</strong> Érica Molina Rubim" },
            { id: "Com-social-dia1", title: "Comunicação social", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala 1<br><strong>Palestrante:</strong> Vanessa de Castro e Letícia Ishikama da Silva" },
            { id: "Agronomia-dia1", title: "Agronomia", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala 2<br><strong>Palestrante:</strong> Mariane Barbará" },
            { id: "Eng-civil-dia1", title: "Engenharia civil", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala 3<br><strong>Palestrante:</strong> Angélica Paiva Ramos" },
            { id: "Psicologia-noturno-dia1", title: "Psicologia", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala A<br><strong>Palestrante:</strong> Marina Castilho de Oliveira e Natália Martinez Vieira" },
            { id: "Direito-noturno-dia1", title: "Direito", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala 1<br><strong>Palestrante:</strong> Marcelo Leal da Silva" },
            { id: "Agronomia-noturno-dia1", title: "Agronomia", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala 2<br><strong>Palestrante:</strong> Mariane Barbará" },
            { id: "Eng-civil-noturno-dia1", title: "Engenharia civil", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala 3<br><strong>Palestrante:</strong> César Fernandes" }
        ]
    },
    {
        day: 2,
        lectures: [
            { id: "motivacional-dia2", title: "A estrada até aqui", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala A<br><strong>Palestrante:</strong> Bruno Scriboni" },
            { id: "comunicaçao-dia2", title: "Entre likes, reuniões e bastidores: a vida real de quem trabalha com comunicação", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala B<br><strong>Palestrante:</strong> Wender Rodrigues" },
            { id: "inf-e-com-dia2", title: "Smartcities e seus desafios", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala C<br><strong>Palestrante:</strong> Claudio Juny Figueiredo" },
            { id: "saude-dia2", title: "Doenças Periodontais", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala 1<br><strong>Palestrante:</strong> Jaqueline Leopoldo" },
            { id: "seguranca-dia2", title: "Primeiros Socorros - Lei Lucas", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala 2<br><strong>Palestrante:</strong> Major Brito" },
            { id: "inf-e-com-2-dia2", title: "Segurança Digital: Riscos, Prevenção e Análise de Casos", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala 3<br><strong>Palestrantes:</strong> Allan Victor Nunes Candido, Maria Julia Sedoguchi Barbosa,Yasmim Bispo Alves, Suzana Florinda Mavuba da Silva" },
            { id: "gestao-noturno-dia2", title: "Falhar faz parte! Como aprender com os erros e criar uma identidade empreendedora", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala A<br><strong>Palestrante:</strong> Denise Martin" },
            { id: "Inf-e-com-noturno-dia2", title: "Arquiteto de software", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala 1<br><strong>Palestrante:</strong> Raul Mariano" },
            { id: "Motivacional-noturno-dia2", title: "Investimentos inteligentes", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala 2<br><strong>Palestrante:</strong> Claudio Juny Figueiredo" },
            { id: "comunicaçao-noturno-dia2", title: "Entre likes, reuniões e bastidores: a vida real de quem trabalha com comunicação", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala 3<br><strong>Palestrante:</strong> Wender Rodrigues" }
        ]
    },
    {
        day: 3,
        lectures: [
            { id: "Oratoria-dia3", title: "Oratória e Apresentação", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala A<br><strong>Palestrante:</strong> Lorena " },
            { id: "E-commerce-dia3", title: "E-commerce", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala 1<br><strong>Palestrante:</strong> Nahany " },
            { id: "maquiagem-dia3", title: "Expressão e Estilo: Aprenda Maquiagem na Prática", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala 2<br><strong>Palestrante:</strong> Emanuelle " },
            { id: "GD-dia3", title: "Game Designer", periodo: "Noturno", description: "<strong>Horário:</strong> 13:20<br><strong>Local:</strong> Sala A<br><strong>Palestrantes:</strong> Daniel, Dherick e Guilherme Mainardi" }
        ]
    },
    {
        day: 4,
        lectures: [
            { id: "motivacional-dia4", title: "Amor ou Medo - A decisão que muda sua vida", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala A<br><strong>Palestrante:</strong> Grazi Cavenaghi" },
            { id: "gestao-dia4", title: "Reduzir, Reulitizar, Reciclar: A Prática da Gestão de Resíduos em Votuporanga", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala B<br><strong>Palestrante:</strong> Vitor Costa" },
            { id: "inf-e-com-dia4", title: "Gestão e IA: Como Usar os \"Superpoderes\" da Inteligência Artificial para Criar o Futuro (e não ser substituído por ele).", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala C<br><strong>Palestrante:</strong> Gean Santos" },
            { id: "saude-dia4", title: "Mente blindada: escolhas que constroem, atitudes que transformam", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala 1<br><strong>Palestrante:</strong> Viviani Buzzo" },
            { id: "inclusao-dia4", title: "Autismo: conhecer para respeitar e incluir", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala 2<br><strong>Palestrante:</strong> Ana Claudia Picolini" },
            { id: "direito-dia4", title: "Direito e Administração: Decidindo hoje o sucesso de amanhã", periodo: "Integral", description: "<strong>Horário:</strong> 10:30<br><strong>Local:</strong> Sala 3<br><strong>Palestrante:</strong> Luciano Silva" },
            { id: "gestao-noturno-dia4", title: "Como administrar seu próprio negócio", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala A<br><strong>Palestrante:</strong> Leandro Poçam" },
            { id: "Inf-e-com-noturno-dia4", title: "Produção de mídias digitais", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala 1<br><strong>Palestrante:</strong> Chritopher Bueno" },
            { id: "seguranca-noturno-dia4", title: "Primeiros Socorros - Lei Lucas", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala 2<br><strong>Palestrante:</strong>Major Brito" },
            { id: "motivacional-noturno-dia4", title: "Amor ou Medo - A decisão que muda sua vida", periodo: "Noturno", description: "<strong>Horário:</strong> 19:30<br><strong>Local:</strong> Sala 3<br><strong>Palestrante:</strong> Grazi Cavenaghi" }
        ]
    },
    {
        day: 5,
        lectures: [
            { id: "GD-dia5", title: "Game Designer", periodo: "Integral", description: "<strong>Horário:</strong> 13:20<br><strong>Local:</strong> Sala A<br><strong>Palestrantes:</strong> Daniel, Dherick e Guilherme Mainardi" },
            { id: "do-re-dia5", title: "Dó-Ré-Ministração", periodo: "Integral", description: "<strong>Horário:</strong> 13:20<br><strong>Local:</strong> Sala B<br><strong>Palestrantes:</strong> Filipe e José Augusto" },
            { id: "Autoimagem-dia5", title: "Autoimagem no ambiente de trabalho", periodo: "Integral", description: "<strong>Horário:</strong> 13:20<br><strong>Local:</strong> Sala C<br><strong>Palestrante:</strong>Anne Marrye, Maria Eduarda" },
            { id: "md-dia5", title: "Feed Criativo-Marketing Digital ", periodo: "Integral", description: "<strong>Horário:</strong> 13:20<br><strong>Local:</strong> Sala 1<br><strong>Palestrantes:</strong> Gabriel e Nathalia" },
            { id: "yt-dia5", title: "Da criação à monetização: domine o YouTube na prática ", periodo: "Integral", description: "<strong>Horário:</strong> 13:20<br><strong>Local:</strong> Sala 2<br><strong>Palestrantes:</strong> Luiz Felipe Oliveira Santos" },
            { id: "criptomoedas-dia5", title: "Do zero ao milhão com criptomoedas", periodo: "Integral", description: "<strong>Horário:</strong> 13:20<br><strong>Local:</strong> Sala 3<br><strong>Palestrantes:</strong> Raphael e Kaiky" }
        ]
    }
];

const elements = {
    authButton: document.getElementById("auth-button"),
    userInfoDiv: document.getElementById("user-info"),
    userName: document.getElementById("user-name"),
    userEmail: document.getElementById("user-email"),
    lecturesContainer: document.getElementById("lectures-by-day-container"),
    deleteMyRegistrationsButton: document.getElementById("delete-my-registrations-button"),
    tabsContainer: document.getElementById("tabs-container"),
    filterIntegralBtn: document.getElementById("filter-integral"),
    filterNoturnoBtn: document.getElementById("filter-noturno"),
    mainContent: document.getElementById("main-content")
};

let allRegistrations = {};
let selectedPeriodoFilter = null;

onAuthStateChanged(auth, user => updateUIForAuthState(user));

function updateUIForAuthState(user) {
    if (user) {
        elements.userName.textContent = user.displayName || "Usuário";
        elements.userEmail.textContent = user.email || "";
        elements.userInfoDiv.classList.remove("hidden");
        elements.deleteMyRegistrationsButton.classList.remove("hidden");
        elements.authButton.textContent = "Sair";
        elements.authButton.onclick = () => signOut(auth);
        document.querySelectorAll(".student-email-input").forEach(input => {
            input.value = user.email || "";
        });
        document.querySelectorAll(".student-name-input").forEach(input => {
            input.value = user.displayName || "";
        });
        return;
    }

    elements.userInfoDiv.classList.add("hidden");
    elements.deleteMyRegistrationsButton.classList.add("hidden");
    elements.authButton.textContent = "Login com Google";
    elements.authButton.onclick = () => signInWithPopup(auth, provider);
    document.querySelectorAll(".student-email-input").forEach(input => {
        input.value = "";
    });
    document.querySelectorAll(".student-name-input").forEach(input => {
        input.value = "";
    });
}

function renderLectures() {
    elements.lecturesContainer.innerHTML = "";

    lecturesByDay.forEach(dayData => {
        if (selectedPeriodoFilter === "Integral" && dayData.day === 3) {
            return;
        }

        if (selectedPeriodoFilter === "Noturno" && dayData.day === 5) {
            return;
        }

        const dayContainer = createDayContainer(dayData);
        elements.lecturesContainer.appendChild(dayContainer);
    });

    updateUIForAuthState(auth.currentUser);
}

function createDayContainer(dayData) {
    const dayContainer = document.createElement("div");
    dayContainer.id = `day-content-${dayData.day}`;
    dayContainer.className = "space-y-6";

    if (dayData.day !== 1) {
        dayContainer.classList.add("hidden");
    }

    if (dayData.lectures.length === 0) {
        dayContainer.innerHTML = '<div class="bg-white p-6 rounded-xl shadow-md text-center text-gray-500">Nenhuma palestra programada para este dia.</div>';
        return dayContainer;
    }

    const filteredLectures = dayData.lectures.filter(lecture => lecture.periodo === selectedPeriodoFilter);

    if (filteredLectures.length === 0) {
        dayContainer.innerHTML = `<div class="bg-white p-6 rounded-xl shadow-md text-center text-gray-500">Nenhuma palestra do período ${selectedPeriodoFilter.toLowerCase()} programada para este dia.</div>`;
        return dayContainer;
    }

    dayContainer.innerHTML = createPeriodHeader(dayData.day);
    filteredLectures.forEach(lecture => {
        dayContainer.appendChild(createLectureCard(dayData.day, lecture));
    });

    return dayContainer;
}

function createPeriodHeader(day) {
    return `
        <div class="periodo-tabs-container mb-4 flex flex-wrap items-center gap-2" data-day="${day}">
            <h5 class="text-lg font-bold text-light">Palestras do Período</h5>
            <span class="text-sm font-bold ${selectedPeriodoFilter === "Integral" ? "text-blue-700" : "text-indigo-500"}">${selectedPeriodoFilter}</span>
        </div>
    `;
}

function createLectureCard(day, lecture) {
    const uniqueIdPrefix = `dia${day}-${lecture.id}`;
    const card = document.createElement("div");

    card.className = "lecture-card bg-white p-6 rounded-xl shadow-md";
    card.id = `card-${uniqueIdPrefix}`;
    card.dataset.periodo = lecture.periodo;
    card.innerHTML = `
        <div class="flex flex-col md:flex-row justify-between items-start md:items-center">
            <div>
                <div class="flex items-center gap-3">
                    <h2 class="text-xl font-bold text-gray-800">${lecture.title}</h2>
                    <span class="text-xs font-bold py-1 px-2.5 rounded-full ${lecture.periodo === "Integral" ? "bg-blue-100 text-blue-800" : "bg-indigo-100 text-indigo-800"}">${lecture.periodo}</span>
                </div>
                <div class="mt-2">
                    <button type="button" class="toggle-description-btn text-sm text-blue-600 hover:underline focus:outline-none" data-target-id="desc-${uniqueIdPrefix}">Ver Detalhes</button>
                    <div id="desc-${uniqueIdPrefix}" class="hidden mt-2 pr-4 text-sm text-gray-600">
                        <div class="rounded-lg border bg-gray-50 p-3">${lecture.description}</div>
                    </div>
                </div>
                <p class="mt-2 font-semibold text-green-600">Vagas Preenchidas: <span id="count-${uniqueIdPrefix}">0</span> / ${TOTAL_SPOTS}</p>
            </div>
            <div id="status-${uniqueIdPrefix}" class="mt-4 rounded-full bg-gray-200 px-4 py-2 text-sm font-medium text-gray-700 md:mt-0">Aguardando...</div>
        </div>
        <div class="mt-4 border-t pt-4">
            <form id="form-${uniqueIdPrefix}" data-lecture-id="${lecture.id}" data-day="${day}" class="space-y-4">
                <p class="text-sm font-medium text-gray-600">Inscreva-se agora:</p>
                <div class="grid grid-cols-1 gap-4 md:grid-cols-2">
                    <div><input type="text" id="name-${uniqueIdPrefix}" required placeholder="Seu nome completo" class="student-name-input w-full rounded-lg border px-4 py-2"></div>
                    <div><input type="email" id="email-${uniqueIdPrefix}" required placeholder="Seu melhor e-mail" class="student-email-input w-full rounded-lg border px-4 py-2"></div>
                </div>
                <div class="grid grid-cols-1 gap-4 md:grid-cols-3">
                    <div>
                        <select id="serie-${uniqueIdPrefix}" required class="w-full rounded-lg border bg-white px-4 py-2 text-gray-700">
                            <option value="" disabled selected>Sua série</option>
                            <option value="1">1ª Série</option>
                            <option value="2">2ª Série</option>
                            <option value="3">3ª Série</option>
                        </select>
                    </div>
                    <div>
                        <select id="curso-${uniqueIdPrefix}" required class="w-full rounded-lg border bg-white px-4 py-2 text-gray-700">
                            <option value="" disabled selected>Seu curso</option>
                            <option value="Administração de Empresas">Administração de Empresas</option>
                            <option value="Informática para Internet">Informática para Internet</option>
                            <option value="Desenvolvimento de Sistemas">Desenvolvimento de Sistemas</option>
                            <option value="Recursos Humanos">Recursos Humanos</option>
                        </select>
                    </div>
                    <div>
                        <select id="periodo-${uniqueIdPrefix}" required class="w-full rounded-lg border bg-white px-4 py-2 text-gray-700">
                            <option value="" disabled selected>Seu período</option>
                            <option value="Integral">Integral</option>
                            <option value="Noturno">Noturno</option>
                        </select>
                    </div>
                </div>
                <div class="flex flex-col gap-3 sm:flex-row">
                    <button type="submit" class="w-full rounded-lg bg-green-600 px-6 py-2 font-semibold text-white sm:w-auto">Confirmar Inscrição</button>
                    <button type="button" class="view-registrations-btn w-full rounded-lg bg-blue-500 px-6 py-2 text-white sm:w-auto" data-unique-id="${uniqueIdPrefix}">Ver Inscritos</button>
                    <button type="button" class="download-pdf-btn w-full rounded-lg bg-gray-600 px-6 py-2 font-semibold text-white sm:w-auto" data-unique-id="${uniqueIdPrefix}">Baixar PDF</button>
                </div>
            </form>
            <div id="message-${uniqueIdPrefix}" class="mt-3 text-sm font-medium"></div>
            <div id="registrations-list-${uniqueIdPrefix}" class="hidden mt-4 max-h-60 overflow-y-auto rounded-lg border bg-gray-50 p-4">
                <h4 class="mb-2 font-bold text-gray-800">Inscritos:</h4>
                <ul id="list-ul-${uniqueIdPrefix}" class="list-inside list-disc space-y-1 text-gray-700"></ul>
            </div>
        </div>
    `;

    return card;
}

function handleTabClick(event) {
    if (!event.target.matches(".tab-btn")) {
        return;
    }

    const selectedDay = event.target.dataset.day;

    elements.tabsContainer.querySelectorAll(".tab-btn").forEach(button => {
        button.classList.toggle("active", button.dataset.day === selectedDay);
    });

    elements.lecturesContainer.querySelectorAll('[id^="day-content-"]').forEach(content => {
        content.classList.toggle("hidden", content.id !== `day-content-${selectedDay}`);
    });
}

function handlePeriodoFilterClick(event) {
    const isIntegral = event.target.id === "filter-integral";
    selectedPeriodoFilter = isIntegral ? "Integral" : "Noturno";

    document.body.classList.toggle("dark-mode", !isIntegral);
    elements.mainContent.classList.remove("hidden");

    elements.filterIntegralBtn.classList.toggle("active", isIntegral);
    elements.filterNoturnoBtn.classList.toggle("active", !isIntegral);

    if (isIntegral) {
        elements.filterIntegralBtn.classList.add("bg-blue-600", "text-white");
        elements.filterIntegralBtn.classList.remove("bg-blue-100", "text-blue-800", "border-blue-300");
        elements.filterNoturnoBtn.classList.add("bg-indigo-100", "text-indigo-800", "border-indigo-300");
        elements.filterNoturnoBtn.classList.remove("bg-indigo-600", "text-white");
    } else {
        elements.filterNoturnoBtn.classList.add("bg-indigo-600", "text-white");
        elements.filterNoturnoBtn.classList.remove("bg-indigo-100", "text-indigo-800", "border-indigo-300");
        elements.filterIntegralBtn.classList.add("bg-blue-100", "text-blue-800", "border-blue-300");
        elements.filterIntegralBtn.classList.remove("bg-blue-600", "text-white");
    }

    document.querySelectorAll(".integral-only").forEach(element => {
        element.classList.toggle("hidden", !isIntegral);
    });

    document.querySelectorAll(".noturno-only").forEach(element => {
        element.classList.toggle("hidden", isIntegral);
    });

    const firstTabForPeriod = elements.tabsContainer.querySelector(".tab-btn:not(.hidden)");
    if (firstTabForPeriod) {
        firstTabForPeriod.click();
    }

    renderLectures();
    listenToRegistrations();
}

async function handleRegistration(event) {
    event.preventDefault();

    const user = auth.currentUser;
    if (!user) {
        displayMessage(event.target.id.replace("form-", ""), "Faça login com Google antes.", "red");
        return;
    }

    const { lectureId, day } = event.target.dataset;
    const uniqueIdPrefix = `dia${day}-${lectureId}`;
    const name = document.getElementById(`name-${uniqueIdPrefix}`).value.trim();
    const email = document.getElementById(`email-${uniqueIdPrefix}`).value.trim().toLowerCase();
    const serie = document.getElementById(`serie-${uniqueIdPrefix}`).value;
    const curso = document.getElementById(`curso-${uniqueIdPrefix}`).value;
    const periodo = document.getElementById(`periodo-${uniqueIdPrefix}`).value;
    const submitButton = event.target.querySelector('button[type="submit"]');
    const palestraInfo = lecturesByDay
        .find(dayData => dayData.day === parseInt(day, 10))
        ?.lectures.find(lecture => lecture.id === lectureId);

    if (palestraInfo && palestraInfo.periodo !== periodo) {
        displayMessage(uniqueIdPrefix, `Inscrição não permitida. Seu período (${periodo}) é diferente do período da palestra (${palestraInfo.periodo}).`, "red");
        return;
    }

    if (!name || !email || !serie || !curso || !periodo) {
        displayMessage(uniqueIdPrefix, "Preencha todos os campos para se inscrever.", "red");
        return;
    }

    submitButton.disabled = true;
    submitButton.textContent = "Processando...";

    try {
        const registrationsByEmailAndDayQuery = query(
            registrationsCollection,
            where("studentEmail", "==", email),
            where("day", "==", parseInt(day, 10))
        );
        const snapshot = await getDocs(registrationsByEmailAndDayQuery);

        if (!snapshot.empty) {
            displayMessage(uniqueIdPrefix, "Você já está inscrito em uma atividade para este dia.", "orange");
            throw "duplicado";
        }

        await addDoc(registrationsCollection, {
            lectureId,
            day: parseInt(day, 10),
            studentName: name,
            studentEmail: email,
            serie,
            curso,
            periodo,
            registeredAt: serverTimestamp(),
            userId: user.uid
        });

        displayMessage(uniqueIdPrefix, "Inscrição realizada com sucesso!", "green");
        event.target.reset();
        updateUIForAuthState(auth.currentUser);
    } catch (error) {
        if (error !== "duplicado") {
            displayMessage(uniqueIdPrefix, "Erro ao registrar. Tente novamente.", "red");
        }
    } finally {
        submitButton.disabled = false;
        submitButton.textContent = "Confirmar Inscrição";
    }
}

async function handleDeleteMyRegistrations() {
    const user = auth.currentUser;

    if (!user || !user.email) {
        alert("Você precisa estar logado para excluir suas inscrições.");
        return;
    }

    if (!confirm("Tem certeza que deseja excluir TODAS as suas inscrições em todos os dias? Esta ação não pode ser desfeita.")) {
        return;
    }

    this.disabled = true;
    this.textContent = "Excluindo...";

    try {
        const registrationsByEmailQuery = query(registrationsCollection, where("studentEmail", "==", user.email));
        const querySnapshot = await getDocs(registrationsByEmailQuery);

        if (querySnapshot.empty) {
            alert("Nenhuma inscrição encontrada para o seu e-mail.");
            return;
        }

        const deletePromises = querySnapshot.docs.map(docSnapshot =>
            deleteDoc(doc(db, "artifacts/default-lecture-app/public/data/registrations", docSnapshot.id))
        );

        await Promise.all(deletePromises);
        alert(`Sucesso! ${deletePromises.length} inscrição(ões) foram excluídas.`);
    } catch (error) {
        console.error("Erro ao excluir inscrições:", error);
        alert("Ocorreu um erro ao tentar excluir suas inscrições. Tente novamente.");
    } finally {
        this.disabled = false;
        this.textContent = "Excluir Minhas Inscrições";
    }
}

function displayMessage(uniqueIdPrefix, text, color) {
    const messageElement = document.getElementById(`message-${uniqueIdPrefix}`);

    messageElement.textContent = text;
    messageElement.className = `mt-3 text-sm font-medium ${MESSAGE_COLORS[color] || "text-gray-600"}`;

    setTimeout(() => {
        messageElement.textContent = "";
    }, 5000);
}

function listenToRegistrations() {
    onSnapshot(registrationsCollection, snapshot => {
        const counts = {};
        const registrations = {};

        lecturesByDay.forEach(dayData => {
            dayData.lectures.forEach(lecture => {
                const uniqueIdPrefix = `dia${dayData.day}-${lecture.id}`;
                counts[uniqueIdPrefix] = 0;
                registrations[uniqueIdPrefix] = [];
            });
        });

        snapshot.docs.forEach(documentSnapshot => {
            const data = documentSnapshot.data();
            const uniqueIdPrefix = `dia${data.day}-${data.lectureId}`;

            if (Object.prototype.hasOwnProperty.call(counts, uniqueIdPrefix)) {
                counts[uniqueIdPrefix] += 1;
                registrations[uniqueIdPrefix].push({
                    name: data.studentName,
                    serie: data.serie,
                    curso: data.curso,
                    periodo: data.periodo
                });
            }
        });

        allRegistrations = registrations;

        lecturesByDay.forEach(dayData => {
            dayData.lectures.forEach(lecture => {
                const uniqueIdPrefix = `dia${dayData.day}-${lecture.id}`;
                const countElement = document.getElementById(`count-${uniqueIdPrefix}`);

                if (!countElement) {
                    return;
                }

                countElement.textContent = counts[uniqueIdPrefix];

                const statusElement = document.getElementById(`status-${uniqueIdPrefix}`);
                const form = document.getElementById(`form-${uniqueIdPrefix}`);
                const submitButton = form.querySelector('button[type="submit"]');

                if (counts[uniqueIdPrefix] >= TOTAL_SPOTS) {
                    statusElement.textContent = "Vagas Esgotadas";
                    statusElement.className = "mt-4 md:mt-0 px-4 py-2 rounded-full text-sm font-medium bg-red-200 text-red-800";
                    submitButton.disabled = true;
                    submitButton.className = "w-full sm:w-auto bg-gray-400 text-white font-semibold px-6 py-2 rounded-lg cursor-not-allowed";
                    return;
                }

                statusElement.textContent = "Vagas Disponíveis";
                statusElement.className = "mt-4 md:mt-0 px-4 py-2 rounded-full text-sm font-medium bg-green-200 text-green-800";
                submitButton.disabled = false;
                submitButton.className = "w-full sm:w-auto bg-green-600 text-white font-semibold px-6 py-2 rounded-lg";
            });
        });
    });
}

function handleDocumentClick(event) {
    if (event.target.matches(".toggle-description-btn")) {
        toggleLectureDescription(event.target);
    }

    if (event.target.matches(".view-registrations-btn")) {
        toggleRegistrationsList(event.target.dataset.uniqueId);
    }

    if (event.target.matches(".download-pdf-btn")) {
        downloadRegistrationsPdf(event.target.dataset.uniqueId);
    }
}

function toggleLectureDescription(button) {
    const target = document.getElementById(button.dataset.targetId);
    target.classList.toggle("hidden");
    button.textContent = target.classList.contains("hidden") ? "Ver Detalhes" : "Ocultar Detalhes";
}

function toggleRegistrationsList(uniqueId) {
    const listElement = document.getElementById(`registrations-list-${uniqueId}`);
    const listItemsContainer = document.getElementById(`list-ul-${uniqueId}`);

    listItemsContainer.innerHTML = "";

    if (allRegistrations[uniqueId] && allRegistrations[uniqueId].length > 0) {
        allRegistrations[uniqueId].forEach(registration => {
            const listItem = document.createElement("li");
            listItem.textContent = `${registration.name} - ${registration.serie}ª Série - ${registration.curso} (${registration.periodo})`;
            listItemsContainer.appendChild(listItem);
        });
    } else {
        listItemsContainer.innerHTML = '<li class="text-gray-500">Nenhum inscrito ainda.</li>';
    }

    listElement.classList.toggle("hidden");
}

function downloadRegistrationsPdf(uniqueId) {
    const [day, lectureId] = uniqueId.replace("dia", "").split("-");
    const lecture = lecturesByDay
        .find(dayData => dayData.day === parseInt(day, 10))
        ?.lectures.find(item => item.id === lectureId);

    if (!lecture) {
        return;
    }

    const { jsPDF } = window.jspdf;
    const pdf = new jsPDF();
    const tableColumn = ["Nome", "Série", "Curso", "Período"];
    const tableRows = (allRegistrations[uniqueId] || []).map(registration => [
        registration.name,
        `${registration.serie}ª Série`,
        registration.curso,
        registration.periodo
    ]);

    pdf.setFontSize(20);
    pdf.text(`Lista de Inscritos - ${lecture.title}`, 14, 22);
    pdf.setFontSize(12);
    pdf.setTextColor(100);
    pdf.text(`Dia: ${day} | Período: ${lecture.periodo}`, 14, 30);
    pdf.autoTable({
        head: [tableColumn],
        body: tableRows,
        startY: 40
    });
    pdf.save(`inscritos-${lecture.title}-dia${day}.pdf`);
}

function setupEventListeners() {
    elements.tabsContainer.addEventListener("click", handleTabClick);
    elements.filterIntegralBtn.addEventListener("click", handlePeriodoFilterClick);
    elements.filterNoturnoBtn.addEventListener("click", handlePeriodoFilterClick);
    elements.deleteMyRegistrationsButton.addEventListener("click", handleDeleteMyRegistrations);
    elements.authButton.addEventListener("click", () => {
        if (auth.currentUser) {
            signOut(auth);
            return;
        }

        signInWithPopup(auth, provider);
    });

    document.addEventListener("click", handleDocumentClick);
    document.addEventListener("submit", event => {
        if (event.target.matches('form[id^="form-"]')) {
            handleRegistration(event);
        }
    });
}

function init() {
    setupEventListeners();
}

init();
